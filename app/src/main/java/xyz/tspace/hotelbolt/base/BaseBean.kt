package xyz.tspace.hotelbolt.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback

/**
 * @param data 具体数据类型
 * @param message 请求结果
 * @param code 结果码
 */
typealias ResponseUnit<T> = Callback<BaseBean<T>>
typealias ResponseList<T> = Callback<BaseBean<List<T>>>

typealias RequestUnit<T> = Call<BaseBean<T>>
typealias RequestList<T> = Call<BaseBean<List<T>>>

data class BaseBean<T>(val message: String, val code: Int, val data: T?)
