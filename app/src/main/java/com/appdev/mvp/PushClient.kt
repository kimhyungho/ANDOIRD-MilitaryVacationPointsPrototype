package com.appdev.mvp

import android.app.Activity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PushClient(activity: Activity) {
    lateinit var service: RetrofitApi

    var activity: Activity = activity

    init {
        this.activity = activity
    }


    val client = OkHttpClient.Builder()
        .build()

    fun push(): RetrofitApi = Retrofit.Builder()
        .baseUrl("http://militarypoint.co.kr:9999/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

}