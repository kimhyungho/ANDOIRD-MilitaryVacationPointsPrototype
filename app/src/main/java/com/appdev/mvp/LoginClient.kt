package com.appdev.mvp

import android.app.Activity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginClient(activity: Activity, uuid: String, email: String?, password: String?) {
    lateinit var service: RetrofitApi

    var activity: Activity = activity


    init {
        this.activity = activity
    }

    val header = Interceptor {
        val original = it.request()
        if (email != null && password != null) {
            val request = original.newBuilder()
                .header("Authorization", uuid)
                .header("email", email)
                .header("password", password)
                .build()
            it.proceed(request)
        } else {
            val request = original.newBuilder()
                .header("Authorization", uuid)
                .build()
            it.proceed(request)
        }
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(header)
        .build()

    fun login(): RetrofitApi = Retrofit.Builder()
        .baseUrl("http://bonbangsasu.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

}