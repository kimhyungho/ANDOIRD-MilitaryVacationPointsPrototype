package com.appdev.mvp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("accounts/auth")
    fun login(
    ): Call<LoginResult>

    @POST("auth")
    fun signUp(

    ): Call<SignUpResult>


//    @POST("post/lol/updatepost")
//    @FormUrlEncoded
//    fun lolupdatepost(
//        @Field("userId") userId: Int,
//        @Field("userNickname") userNickname: String,
//        @Field("postId") postId: Int,
//        @Field("gameMode") gameMode: String,
//        @Field("title") title: String,
//        @Field("startTier") startTier: Int,
//        @Field("endTier") endTier: Int,
//        @Field("startTime") starTime: Date,
//        @Field("content") content: String,
//        @Field("headCount") headCount: Int,
//        @Field("top") top: Int,
//        @Field("bottom") bottom: Int,
//        @Field("mid") mid: Int,
//        @Field("jungle") jungle: Int,
//        @Field("support") support: Int,
//        @Field("talkon") talkon: Int
//    ): Call<Msg>


}