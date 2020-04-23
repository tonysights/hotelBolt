package xyz.tspace.hotelbolt.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haibin.calendarview.Calendar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.tspace.hotelbolt.MyApp
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseBean
import xyz.tspace.hotelbolt.base.BaseViewModel
import xyz.tspace.hotelbolt.base.ResponseList
import xyz.tspace.hotelbolt.base.ResponseUnit
import xyz.tspace.hotelbolt.http.api.HotelService
import xyz.tspace.hotelbolt.http.api.ImageService
import xyz.tspace.hotelbolt.http.api.UserService
import xyz.tspace.hotelbolt.model.*
import java.util.*


class MainViewModel(application: Application) : BaseViewModel(application) {

    val initToken = Token(getString(R.string.token_init))

    val expiredToken = Token(getString(R.string.token_expired))

    //记录用户信息的SharedPreference
    private val userShp = getApplication<MyApp>().getSharedPreferences(
        getString(R.string.key_userShp),
        Context.MODE_PRIVATE
    )

    //实时用户信息
    private val _userLive = MutableLiveData<User>(DataProvider.getInitUser())
    val userLive: LiveData<User> get() = _userLive

    //实时token信息

    private val _tokenLive = MutableLiveData<Token>(initToken)
        get() {
            //分别尝试从此liveData, application和sharedPreference中获取有效token，直至最后才返回null,然后提示用户需要重新登陆
            val temp1 = field.value
            val temp2 by lazy { getApplication<MyApp>().currentToken }
            val temp3: Token by lazy {
                val temp3Str = userShp.getString(getString(R.string.key_token), null)
                val temp3TIme = userShp.getLong(getString(R.string.key_token_createdTime), 0)
                Token(temp3Str, Date(temp3TIme))
            }
            //每次使用token发送请求前，都会在token提供方（此liveData）和接受方（observer）进行双重校验
            return field.also {
                it.postValue(
                    when {
                        verifyToken(temp1) -> field.value
                        verifyToken(temp2) -> temp2
                        verifyToken(temp3) -> temp3
                        else -> expiredToken
                    }
                )
            }
        }
    val tokenLive: LiveData<Token> get() = _tokenLive


    //预定界面日期选择器数据
    var rangeCalendarSaves: MutableLiveData<Array<Calendar>> = MutableLiveData()


    //轮播图数据
    private val _homeBannerLiveLive = MutableLiveData(DataProvider.getPreviewImgList())
    val homeBannerLiveData: LiveData<List<PixabayImage>> get() = _homeBannerLiveLive

    //实时各酒店信息
    private val _hotelListLive = MutableLiveData<List<Hotel>>()
    val hotelListLive: LiveData<List<Hotel>> get() = _hotelListLive

    //实时所选酒店各房间信息
    private val _hotelRoomLive =
        MutableLiveData<List<HotelRoom>>(DataProvider.getInitHotelRoomList())
    val hotelRoomLive: LiveData<List<HotelRoom>> get() = _hotelRoomLive

    //房间评论信息
    private val _roomCommentsLive = MutableLiveData<List<Appraise>>(listOf<Appraise>())
    val roomCommentsLive: LiveData<List<Appraise>> get() = _roomCommentsLive

    //
    val userPraiseFlLive = MutableLiveData<Float>(0F)


    //抓取轮播图数据
    fun fetchBannerData() {

        ImageService.fetchImageInfo("hotel", object : Callback<Pixabay> {
            override fun onFailure(call: Call<Pixabay>, t: Throwable) {
                Log.e(TAG, "获取Pixabay数据失败，原因如下：${t.cause}")
            }

            override fun onResponse(call: Call<Pixabay>, response: Response<Pixabay>) {

                if (response.isSuccessful) {
                    val list = response.body()?.hits?.asList()
                    _homeBannerLiveLive.postValue(list)
                    Log.i(TAG, "获取图片数据成功！共${list?.size}条记录")
                }
            }
        })
    }

    //校验token
    private fun verifyToken(token: Token?): Boolean {
        if (token != null && token.isValid) return true
        return false
    }

    //获取用户信息
    fun fetchUserInfo() {
        val token = _tokenLive.value
        if (token != null && token.isValid) {
            UserService.fetchUserInfo(token, object : ResponseUnit<User> {
                override fun onFailure(call: Call<BaseBean<User>>, t: Throwable) {
                    Log.i("TAG", t.cause.toString())
                }

                override fun onResponse(
                    call: Call<BaseBean<User>>,
                    response: Response<BaseBean<User>>
                ) {
                    val temp = response.body()?.data
                    if (temp != null)
                        _userLive.postValue(temp)
                }
            })
        }
    }

    //获取酒店信息
    fun fetchAllHotelInfo() {
        val token = _tokenLive.value
        if (token != null && token.isValid) {
            HotelService.searchHotel(token, SearchRoom(0), object : ResponseList<Hotel> {
                override fun onFailure(call: Call<BaseBean<List<Hotel>>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<BaseBean<List<Hotel>>>,
                    response: Response<BaseBean<List<Hotel>>>
                ) {
                    response.body()?.data.let { _hotelListLive.postValue(it) }
                }

            })
        }
    }

    fun fetchHotelRoomInfo(hotelId: String) {
        val token = _tokenLive.value
        if (token != null && token.isValid)
        //TODO: 将hotelId参数修改为页面实际选择的酒店的hotelId
            HotelService.searchRoomByHotelId(
                token, "1", object : ResponseList<HotelRoom> {
                    override fun onFailure(call: Call<BaseBean<List<HotelRoom>>>, t: Throwable) {
                        println(t.cause)
                    }

                    override fun onResponse(
                        call: Call<BaseBean<List<HotelRoom>>>,
                        response: Response<BaseBean<List<HotelRoom>>>
                    ) {
                        response.body()?.data?.let { _hotelRoomLive.postValue(it) }
                    }

                })

    }

    fun fetchRoomComment(roomTypeId: String) {
        val token = _tokenLive.value
        if (token != null && token.isValid)
        //todo: hotelId这个参数需要修正
            HotelService.findAllComments(token, "1", object : ResponseList<Appraise> {
                override fun onFailure(call: Call<BaseBean<List<Appraise>>>, t: Throwable) {
                    println(t.cause.toString())
                }

                override fun onResponse(
                    call: Call<BaseBean<List<Appraise>>>,
                    response: Response<BaseBean<List<Appraise>>>
                ) {
                    response.body()?.data?.let { _roomCommentsLive.postValue(it) }

                }
            })


    }


}