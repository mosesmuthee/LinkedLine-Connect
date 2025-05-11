//package com.moses.linkedlineconnect.Networks.Mpesa
//
//interface MpesaApi {
//    @GET("oauth/v1/generate?grant_type=client_credentials")
//    fun getAccessToken(
//        @Header("Authorization") auth: String
//    ): Call<AccessTokenResponse>
//
//    @POST("mpesa/stkpush/v1/processrequest")
//    fun stkPush(
//        @Header("Authorization") auth: String,
//        @Body request: StkPushRequest
//    ): Call<StkPushResponse>
//}
