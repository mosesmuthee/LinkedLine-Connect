//package com.moses.linkedlineconnect.Networks.Mpesa
//
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
