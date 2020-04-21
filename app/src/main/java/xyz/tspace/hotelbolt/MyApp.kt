package xyz.tspace.hotelbolt

import android.app.Application
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import xyz.tspace.hotelbolt.model.Token

class MyApp : Application() {
    companion object {


    }


    //APP中正在使用的token实例，由各viewModel传入
    var currentToken: Token? = null
        set(value) {
            if (value != null && value.isValid) field = value
            else field = null
        }
        get() {
            val temp = field
            if (temp != null && temp.isValid) return temp
            return null
        }


    override fun onCreate() {
        super.onCreate()
        QMUISwipeBackActivityManager.init(this)
    }
}