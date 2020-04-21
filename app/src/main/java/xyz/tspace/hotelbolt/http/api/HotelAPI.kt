package xyz.tspace.hotelbolt.http.api

import retrofit2.http.*
import xyz.tspace.hotelbolt.base.RequestList
import xyz.tspace.hotelbolt.base.ResponseList
import xyz.tspace.hotelbolt.http.HttpToolBox
import xyz.tspace.hotelbolt.model.Hotel
import xyz.tspace.hotelbolt.model.HotelRoom
import xyz.tspace.hotelbolt.model.SearchRoom
import xyz.tspace.hotelbolt.model.Token

interface HotelAPI {

    @GET("api/hotel/findroombyhotelid/{hotelId}")
    fun searchRoomByHotelId(
        @Header("akt") token: String,
        @Path("hotelId") hotelId: String

    ): RequestList<HotelRoom>

    @POST("api/hotel/findhotel")
    fun searchHotel(
        @Header("akt") tokenStr: String,
        @Body searchRoom: SearchRoom
    ): RequestList<Hotel>


}

class HotelService {
    companion object {
        private val service: HotelAPI by lazy { HttpToolBox.client.create(HotelAPI::class.java) }

        fun searchRoomByHotelId(
            token: Token,
            hotelId: String,
            callback: ResponseList<HotelRoom>
        ) {
            service.searchRoomByHotelId(token.akt, hotelId).run { enqueue(callback) }
        }

        fun searchHotel(token: Token, searchRoom: SearchRoom, callback: ResponseList<Hotel>) {
            service.searchHotel(token.akt, searchRoom).run { enqueue(callback) }
        }


    }
}