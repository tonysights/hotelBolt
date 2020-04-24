package xyz.tspace.hotelbolt.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import xyz.tspace.hotelbolt.MyApp
import xyz.tspace.hotelbolt.base.BaseBean
import xyz.tspace.hotelbolt.base.BaseViewModel
import xyz.tspace.hotelbolt.base.ResponseList
import xyz.tspace.hotelbolt.http.api.HotelService
import xyz.tspace.hotelbolt.model.*

class HotelViewModel(application: Application) : BaseViewModel(application) {

    val userPraiseFlLive = MutableLiveData<Float>(0F)

    private val currentToken get() = getApplication<MyApp>().currentToken

    //实时各酒店信息
    private val _hotelListLive = MutableLiveData<List<Hotel>>()
    val hotelListLive: LiveData<List<Hotel>> get() = _hotelListLive


    //所选酒店各房间信息
    private val _hotelRoomLive =
        MutableLiveData<List<HotelRoom>>(DataProvider.getInitHotelRoomList())
    val roomListLive: LiveData<List<HotelRoom>> get() = _hotelRoomLive


    //房间评论信息
    private val _roomCommentsLive = MutableLiveData<List<Appraise>>(listOf<Appraise>())
    val roomCommentsLive: LiveData<List<Appraise>> get() = _roomCommentsLive


    //获取酒店信息列表
    fun fetchHotelList() {
        val token = currentToken
        if (token != null && token.isValid) {
            HotelService.searchHotel(token, SearchRoom(0), object : ResponseList<Hotel> {
                override fun onFailure(call: Call<BaseBean<List<Hotel>>>, t: Throwable) {
                    Log.i(TAG, t.message.toString())
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


    //获取酒店房间信息列表
    fun fetchRoomListByHotelId(hotelId: String = "1") {
        val token = currentToken
        if (token != null && token.isValid)
        //TODO: 将hotelId参数修改为页面实际选择的酒店的hotelId
            HotelService.searchRoomByHotelId(
                token, hotelId, object : ResponseList<HotelRoom> {
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


    //获取房间评论列表
    fun fetchRoomCommentByRoomTypeId(roomTypeId: String) {
        val token = currentToken
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