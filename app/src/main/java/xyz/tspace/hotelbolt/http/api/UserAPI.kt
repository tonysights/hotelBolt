package xyz.tspace.hotelbolt.http.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import xyz.tspace.hotelbolt.base.RequestUnit
import xyz.tspace.hotelbolt.base.ResponseUnit
import xyz.tspace.hotelbolt.http.HttpToolBox
import xyz.tspace.hotelbolt.model.Token
import xyz.tspace.hotelbolt.model.User


interface UserAPI {

    //登陆
    @POST("api/user/login/pas")
    fun signIn(@Body user: User): RequestUnit<Token>

    //注册
    @POST("api/register/userinsert")
    fun signUp(@Body user: User): RequestUnit<Unit>

    //获取个人信息
    @GET("api/register/info")
    fun fetchUserInfo(@Header("akt") tokenStr: String): RequestUnit<User>

    //修改个人信息
    @POST("api/register/userchange")
    fun modifyPersonalInfo(@Header("akt") tokenStr: String, @Body user: User): RequestUnit<Unit>
}

class UserService {
    companion object {
        private val service: UserAPI by lazy { HttpToolBox.client.create(UserAPI::class.java) }


        fun signIn(loginUser: User, callback: ResponseUnit<Token>) {
            service.signIn(loginUser).run { enqueue(callback) }
        }


        fun signUp(regUser: User, callback: ResponseUnit<Unit>) {
            service.signUp(regUser).run { enqueue(callback) }
        }

        fun fetchUserInfo(token: Token, callback: ResponseUnit<User>) {
            service.fetchUserInfo(token.akt).run { enqueue(callback) }
        }

        fun modifyPersonalInfo(token: Token, modifiedUser: User, callback: ResponseUnit<Unit>) {
            service.modifyPersonalInfo(token.akt, modifiedUser).run { enqueue(callback) }
        }
    }
}