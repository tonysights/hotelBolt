package xyz.tspace.hotelbolt.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpToolBox {
    companion object {

        private const val baseUrl = "http://120.26.235.201:8799/"
        private const val pixabayUrl = "https://pixabay.com/"

        val client: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val imageClient: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(pixabayUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}