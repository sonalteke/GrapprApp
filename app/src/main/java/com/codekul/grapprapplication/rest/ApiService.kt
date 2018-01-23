package com.codekul.grapprapplication.rest

import com.codekul.grapprapplication.domain.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by sonal on 30/12/17.
 */
interface ApiService {

    @Headers("Accept: application/json","Content-Type: application/json")

    @GET("app/getApps")
    fun getApps():Call<AppsInfo>

    @POST("user/registerUser")
    fun registerUser(@Body user: User):Call<DtoUser>

    @POST("user/userLogin")
    fun userLogin(@Body credential:Credential):Call<DtoLogin>

    @POST("appStack/saveAppStack")
    fun updateUsers(@Body appStack: AppStack):Call<StackInfo>

    @GET("user/getUserAppsCount/{id}")
    fun getUserAppsCount(@Path("id") id: String):Call<CountInfo>

    companion object {

        private val PROTOCOL: String = "http"
        private val SERVER: String = "192.168.0.101"
        private val PORT: String = "8080"
        private val APP_NAME: String = "cashbolo/api"
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