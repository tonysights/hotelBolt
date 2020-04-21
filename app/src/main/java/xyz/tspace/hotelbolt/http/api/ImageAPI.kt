package xyz.tspace.hotelbolt.http.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.QueryMap
import xyz.tspace.hotelbolt.http.HttpToolBox
import xyz.tspace.hotelbolt.model.Pixabay

interface ImageAPI {

    @GET("api")
    fun fetchImageInfo(@QueryMap map: Map<String, String>): Call<Pixabay>
}


class ImageService {
    companion object {
        val key = "15368156-94ad84a3f565219a5b6e8e233"
        private val service: ImageAPI by lazy { HttpToolBox.imageClient.create(ImageAPI::class.java) }
        fun fetchImageInfo(keywords: String, callback: Callback<Pixabay>, imgNum: Int = 7) {
            val map = mapOf(
                "key" to key,
                "q" to keywords,
                "per_page" to imgNum.toString()
            )
            val call = service.fetchImageInfo(map)
            call.enqueue(callback)
        }
    }
}

