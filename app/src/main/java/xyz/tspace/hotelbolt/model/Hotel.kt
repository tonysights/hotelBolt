package xyz.tspace.hotelbolt.model

data class SearchRoom(val lowPrice: Int) {
    val startTime: String? = null
    val countList: List<String>? = null
    val endTie: String? = null
    val highPrice: String? = null
    val hotelComment: String? = null
    val hotelRank: String? = null
    val hotelSource: Float? = null
    val priorityMode: Int? = null
    val ssList: List<String>? = null

}

data class RoomType(val hotelId: String) {
    val avgGrade: Float? = null
    val bedInfo: String? = null
    val breakfast: String? = null
    val castPolicy: String? = null
    val convenienceFacilities: String? = null
    val createTime: String? = null
    val creater: String? = null
    val facilitiesGrade: Float? = null
    val hygieneGrade: String? = null
    val id: String? = null
    val locationGrade: Float? = null
    val membershipInterests: String? = null
    val roomArea: String? = null
    val roomCount: Float? = null
    val roomDetails: String? = null
    val roomName: String? = null
    val roomOrgprice: Int? = null
    val roomPrice: Float? = null
    val roomSize: Int? = null
    val roomSpace: Int? = null
    val roomType: String? = null
    val serviceGrade: String? = null
    val urlList: List<String>? = null
    val userIntegral: Int? = null
    val washroomCollocation: String? = null
    val wifiInfo: String? = null
    val windows: String? = null
}

data class Hotel(
    val id: String,
    val hotelName: String,
    val hotelDetail: String,
    val hotelRank: String,
    val hotelSource: Float,
    val hotelLocation: String,
    val hotelPic: String,
    val hotelCountpay: Int,
    val hotelComment: Int,
    val logoList: List<String>,
    val hotelMaxprice: Float,
    val lowPrice: Int,
    val orgPrice: Int,
    val hotelLevel: Int
)

data class HotelRoom(
    val id: Int,
    val roomNumber: Int,
    val roomStatus: Int,
    val roomtypeId: Int,
    val fixed: Int,
    val needClean: Int,
    val floor: Int,
    val hotelid: Int,
    val status: Int
)