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
            //分别尝试从此liveData, application和sharedPreference中获取有效token，直至最后返回null,然后提示用户需要重新登陆
            val temp1 = field.value
            val temp2 by lazy { getApplication<MyApp>().currentToken }
            val temp3: Token by lazy {
                val temp3Str = userShp.getString(getString(R.string.key_token), null)
                val temp3TIme = userShp.getLong(getString(R.string.key_token_createdTime), 0)
                Token(temp3Str, Date(temp3TIme))
            }
            //每次使用token发送请求前，都会在token提供方（此liveData）和接受方（observer）进行双重校验
            field.value = when {
                verifyToken(temp1) -> field.value
                verifyToken(temp2) -> temp2
                verifyToken(temp3) -> temp3
                else -> expiredToken
            }

            return field
        }
    val tokenLive: LiveData<Token> get() = _tokenLive

    init {

    }

    //预定界面日期选择器数据
    var rangeCalendarSaves: MutableLiveData<Array<Calendar>> = MutableLiveData()


    //轮播图数据
    private val _homeBannerLiveLive = MutableLiveData(DataProvider.getPreviewImgList())
    val homeBannerLiveData: LiveData<List<PixabayImage>> get() = _homeBannerLiveLive




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






}