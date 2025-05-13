package com.moses.linkedlineconnect.payment

import android.os.Build
import androidx.annotation.RequiresApi
import com.moses.linkedlineconnect.api.MpesaApi
import okhttp3.Response

object Mpesa {
    @RequiresApi(Build.VERSION_CODES.O)
    fun processPayment(phoneNumber: String, amount: Int, transactionDesc: String): Response {
        return MpesaApi.initiateStkPush(phoneNumber, amount, transactionDesc)
    }
}