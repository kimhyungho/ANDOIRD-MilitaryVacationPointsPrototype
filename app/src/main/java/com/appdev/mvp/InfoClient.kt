package com.appdev.mvp

import android.app.Activity
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoClient(activity: Activity, email: String?) {
    lateinit var service: RetrofitApi

    var activity: Activity = activity

    init {
        this.activity = activity
    }

    val header = Interceptor {
        val original = it.request()
        if (email != null) {
            val request = original.newBuilder()
                .header("email", email)
                .build()
            it.proceed(request)
        }else {
            it.proceed(original)
        }
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(header)
        .build()


    fun getInfo(): RetrofitApi = Retrofit.Builder()
        .baseUrl("http://bonbangsasu.com:7777/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)


}