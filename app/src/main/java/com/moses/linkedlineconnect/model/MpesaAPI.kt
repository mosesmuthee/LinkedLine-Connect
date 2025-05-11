//package com.moses.linkedlineconnect.model
//
//import android.telecom.Call
//import com.google.gson.annotations.SerializedName
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.Header
//import retrofit2.http.POST
//
//data class AccessTokenResponse(   @SerializedName("access_token") val accessToken: String,
//                                  @SerializedName("expires_in") val expiresIn: String
//)
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
//data class MpesaRequest(
//    val phoneNumber: String,
//    val amount: String
//)
//
//data class MpesaResponse(
//    val message: String,
//    val checkoutRequestID: String? = null
//)
//object RetrofitClient {
//    private const val BASE_URL = "https://sandbox.safaricom.co.ke/" // change to live for production
//
//    val instance: MpesaApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MpesaApi::class.java)
//    }
//}