package xyz.tspace.hotelbolt.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import xyz.tspace.hotelbolt.MyApp

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = this.javaClass.name
    fun getString(resId: Int) = getApplication<MyApp>().getString(resId)
    fun getInteger(resId :Int) = getApplication<MyApp>().resources.getInteger(resId)


}