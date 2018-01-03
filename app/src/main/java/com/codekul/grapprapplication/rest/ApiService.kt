package com.codekul.grapprapplication.rest

import com.codekul.grapprapplication.domain.Apps
import com.codekul.grapprapplication.dto.App
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by sonal on 30/12/17.
 */
interface ApiService {

    @Headers("Accept: application/json","Content-Type: application/json")

    @GET("getApps")
    fun getApps():Call<List<Apps>>

    companion object {

        private val PROTOCOL: String = "http"
        private val SERVER: String = "192.168.0.116"
        private val PORT: String = "8080"
        private val APP_NAME: String = "apps"
        val BASE_URL = "$PROTOCOL://$SERVER:$PORT/$APP_NAME/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}