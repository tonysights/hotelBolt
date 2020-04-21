package xyz.tspace.hotelbolt

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.tspace.hotelbolt.base.BaseBean
import xyz.tspace.hotelbolt.base.ResponseList
import xyz.tspace.hotelbolt.base.ResponseUnit
import xyz.tspace.hotelbolt.http.api.HotelService
import xyz.tspace.hotelbolt.http.api.ImageService
import xyz.tspace.hotelbolt.http.api.UserService
import xyz.tspace.hotelbolt.model.*
import kotlin.random.Random

val token = Token(
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJ7XCJpZFwiOlwiNjJcIixcImxvZ2luY29kZVwiOlwiMTAxMTAxMVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCROOXFmU0hvclRZLzE3Ly43Tnd2Nk91TEMyVlMwYTl1VjNmSTlmUHNPeUhLaWJEYWRwUGhES1wiLFwidXNlcm5hbWVcIjpcIui_measoeW6lOivpeS4jeS8mumUmeS6hlwifSIsInNjb3BlIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiLCJST0xFX0FQSSJdLCJleHAiOjE1ODY5MzY4MjksImF1dGhvcml0aWVzIjpbIlVTRVIiLCJDR0lORk8iXSwianRpIjoiOWQyNThiM2EtNWVlZC00MzdlLTllNDAtYjIwMzQzMzRjYTk2IiwiY2xpZW50X2lkIjoiYzEifQ.ZoGsqQFXS4J8iRllnuDiWHMy5Sml06jjaOkVIuMEBTc"
)

fun main() {


    testModifiedUser()


}

fun testLogin() {
    val num: Int = Random(10).nextInt(20)
    val user: User = User(logincode = "2017023", password = "2017023")
//    val user: User = User( logincode = "2017023", password = "2017023")
    UserService.signIn(user, object : ResponseUnit<Token> {
        override fun onFailure(call: Call<BaseBean<Token>>, t: Throwable) {
            println("请求失败")
        }

        override fun onResponse(call: Call<BaseBean<Token>>, response: Response<BaseBean<Token>>) {
            println(response.body()?.data?.token)
        }
    })
}


fun testImage() {
    val keywords = "hotel"
    ImageService.fetchImageInfo(keywords, object : Callback<Pixabay> {

        override fun onFailure(call: Call<Pixabay>, t: Throwable) {
            println("获取图片失败")
        }

        override fun onResponse(call: Call<Pixabay>, response: Response<Pixabay>) {
            val array: Array<PixabayImage>? = response.body()?.hits
            val num = array?.size
            println("供获得$num 张图片")
        }

    })

}

fun testFindHotelByHotelId() {


    HotelService.searchRoomByHotelId(token, "1", object : ResponseList<HotelRoom> {
        override fun onFailure(call: Call<BaseBean<List<HotelRoom>>>, t: Throwable) {
            println()
        }

        override fun onResponse(
            call: Call<BaseBean<List<HotelRoom>>>,
            response: Response<BaseBean<List<HotelRoom>>>
        ) {
            println(response.body()?.data.toString())
        }

    })
}

fun testFindHotel() {

    HotelService.searchHotel(token, SearchRoom(0), object : ResponseList<Hotel> {
        override fun onFailure(call: Call<BaseBean<List<Hotel>>>, t: Throwable) {
            print(t.cause)
        }

        override fun onResponse(
            call: Call<BaseBean<List<Hotel>>>,
            response: Response<BaseBean<List<Hotel>>>
        ) {
            val list = response.body()?.data
            val iterator = list?.iterator()
            while (iterator!!.hasNext()) {
                println(iterator.next().hotelName)
            }
        }
    })
}

fun testFetchPersonalInfo() {
    UserService.fetchUserInfo(token, object : ResponseUnit<User> {
        override fun onFailure(call: Call<BaseBean<User>>, t: Throwable) {
            println(t.cause)
        }

        override fun onResponse(call: Call<BaseBean<User>>, response: Response<BaseBean<User>>) {
            println(response.body()?.data?.username)
        }
    })
}

fun testModifiedUser() {

    val temUser = User(logincode = "1011011", password = "12345")
    UserService.modifyPersonalInfo(token, temUser, object : ResponseUnit<Unit> {
        override fun onFailure(call: Call<BaseBean<Unit>>, t: Throwable) {
            println(t.cause.toString())
        }

        override fun onResponse(call: Call<BaseBean<Unit>>, response: Response<BaseBean<Unit>>) {
            println(response.body()?.message)
        }
    })
}