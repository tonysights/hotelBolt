package xyz.tspace.hotelbolt.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import xyz.tspace.hotelbolt.MyApp
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseBean
import xyz.tspace.hotelbolt.base.BaseViewModel
import xyz.tspace.hotelbolt.base.ResponseUnit
import xyz.tspace.hotelbolt.http.api.UserService
import xyz.tspace.hotelbolt.model.Token
import xyz.tspace.hotelbolt.model.User
import java.util.*

class LaunchViewModel(application: Application) :
    BaseViewModel(application) {

    //标记：根据token是否有效决定能不能跳过登陆流程
    val canPassLogin: Boolean
        get() {
            val token = _tokenLive.value
            if (token != null && token.isValid) {
                getApplication<MyApp>().currentToken = token
                return true
            }
            return false
        }

    private val shp: SharedPreferences = getApplication<MyApp>().getSharedPreferences(
        getString(R.string.key_userShp),
        Context.MODE_PRIVATE
    )


    //Token
    private val _tokenLive = MutableLiveData<Token?>(loadToken())
    val tokenLive: LiveData<Token?> get() = _tokenLive


    //登陆
    fun login(userNameInput: String, passwordInput: String) {

        val loginUser = User(userNameInput, passwordInput)
        UserService.signIn(loginUser, object : ResponseUnit<Token> {


            override fun onFailure(call: Call<BaseBean<Token>>, t: Throwable) {
                Log.i(TAG, "登陆失败，原因：" + t.cause.toString())
            }

            override fun onResponse(
                call: Call<BaseBean<Token>>,
                response: Response<BaseBean<Token>>
            ) {
                val temp = response.body()?.data.also { it?.verify() }

                when {
                    temp == null -> _tokenLive.value = Token(null).also {
                        it.verifyInfo = getInteger(R.integer.LOGIN_FAIL)
                    }

                    temp.isValid -> {
                        _tokenLive.value =
                            temp.apply { verifyInfo = getInteger(R.integer.LOGIN_SUCCESS) }
                        saveToken(mToken = temp, loginUser = loginUser)
                    }
                }
            }
        })
    }


    //sharedPreference保存信息
    private fun saveToken(mToken: Token, loginUser: User) {
        shp.edit {
            putString(getString(R.string.key_loginCode), loginUser.logincode)
            putString(getString(R.string.key_token), mToken.token)
            putLong(getString(R.string.key_token_createdTime), mToken.createdDate.time)
        }
        getApplication<MyApp>().currentToken = mToken
    }


    //加载sharedPreference
    private fun loadToken(): Token? {
        if (shp.contains(getString(R.string.key_token_createdTime))) {
            val oldToken = Token(
                shp.getString(getString(R.string.key_token), null),
                Date(shp.getLong(getString(R.string.key_token_createdTime), 1586762897031))
            )
            if (oldToken.isValid) return oldToken
        }
        return null
    }


}





