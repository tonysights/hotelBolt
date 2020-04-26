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
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJleHAiOjE1ODc0NTcwMTgsInVzZXJfbmFtZSI6IntcImlkXCI6XCI2NFwiLFwibG9naW5jb2RlXCI6XCIyMDE3MDIzXCIsXCJwYXNzd29yZFwiOlwiJDJhJDEwJDNhblJaT0thLlhCbmJmbGV5MjUvV09aLmE4VEM3Ukp5dnRnOHFKblJ4L2tBdS9odS5JS2htXCIsXCJ1c2VybmFtZVwiOlwiMjAxNzAyM1wifSIsImp0aSI6ImE0NTI5MDUyLWI4MzctNGI4Mi05NDhhLTZlN2RmYzhmN2ZlZCIsImNsaWVudF9pZCI6ImMxIiwic2NvcGUiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiIsIlJPTEVfQVBJIl19.wdXWwRo0RAQbubyl0WN0zYsQrqXCF17xo6XVVJ6B2ws"
   )

fun main() {

    testFindComments()



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


    HotelService.searchRoomByHotelId(token, "1", object : ResponseList<RoomType> {
        override fun onFailure(call: Call<BaseBean<List<RoomType>>>, t: Throwable) {
            println()
        }

        override fun onResponse(
            call: Call<BaseBean<List<RoomType>>>,
            response: Response<BaseBean<List<RoomType>>>
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

fun testFindComments() {
    HotelService.findAllComments(token, "1", callback = object : ResponseList<Appraise> {
        override fun onFailure(call: Call<BaseBean<List<Appraise>>>, t: Throwable) {
            println(t.message)
        }

        override fun onResponse(
            call: Call<BaseBean<List<Appraise>>>,
            response: Response<BaseBean<List<Appraise>>>
        ) {
            println(response.body()?.data)
        }

    })

}