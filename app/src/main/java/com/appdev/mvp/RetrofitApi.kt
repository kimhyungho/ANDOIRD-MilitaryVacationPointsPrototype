package com.appdev.mvp

import androidx.annotation.CheckResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*


interface RetrofitApi {

    @Headers(
        "accept: application/json",
        "content-type: application/json"
    )
    @GET("accounts/auth/emailcheck")
    fun emailCheck(
    ): Call<EmailCheckResult>

    @Headers(
        "accept: application/json",
        "content-type: application/json"
    )
    @GET("accounts/auth")
    fun login(
    ): Call<LoginResult>

    @GET("accounts/info/pointlist")
    fun getPoint(
    ): Call<PointResult>

    @GET("accounts/info/vacationlist")
    fun getVacation(
    ): Call<VacationResult>


    @Headers(
        "accept: application/json",
        "content-type: application/json"
    )
    @POST("accounts/auth")
    fun signUp(
        @Body body: SignUpRequest
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