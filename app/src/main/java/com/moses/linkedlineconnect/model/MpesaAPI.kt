package com.moses.linkedlineconnect.api

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.util.Base64

object MpesaApi {
    private const val BASE_URL = "https://sandbox.safaricom.co.ke"
    private const val CONSUMER_KEY = "A7D9OOzd5TMjBR3RiKya82oyC1E9WIq4NfrKUlAjA4qS7HOE"
    private const val CONSUMER_SECRET = "GneEzl0K6e8X8kXG4XZNwG7djLMDxmWmqW8dMB16TSiA6dHZGRXA6MU0W46JbiMu"
    private const val BUSINESS_SHORT_CODE = "174379"
    private const val PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"
    private const val CALLBACK_URL = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest"

    private val client = OkHttpClient()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAccessToken(): String {
        val credentials = "$CONSUMER_KEY:$CONSUMER_SECRET"
        val encodedCredentials = Base64.getEncoder().encodeToString(credentials.toByteArray())

        val request = Request.Builder()
            .url("$BASE_URL/oauth/v1/generate?grant_type=client_credentials")
            .addHeader("Authorization", "Basic $encodedCredentials")
            .build()

        val response = client.newCall(request).execute()
        val jsonResponse = JSONObject(response.body?.string() ?: "")
        return jsonResponse.getString("access_token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initiateStkPush(phoneNumber: String, amount: Int, transactionDesc: String): Response {
        val accessToken = getAccessToken()
        val timestamp = System.currentTimeMillis().toString()
        val password = Base64.getEncoder().encodeToString("$BUSINESS_SHORT_CODE$PASSKEY$timestamp".toByteArray())

        val payload = JSONObject()
        payload.put("BusinessShortCode", BUSINESS_SHORT_CODE)
        payload.put("Password", password)
        payload.put("Timestamp", timestamp)
        payload.put("TransactionType", "CustomerPayBillOnline")
        payload.put("Amount", amount)
        payload.put("PartyA", phoneNumber)
        payload.put("PartyB", BUSINESS_SHORT_CODE)
        payload.put("PhoneNumber", phoneNumber)
        payload.put("CallBackURL", CALLBACK_URL)
        payload.put("AccountReference", "LinkedLineConnect")
        payload.put("TransactionDesc", transactionDesc)

        val request = Request.Builder()
            .url("$BASE_URL/mpesa/stkpush/v1/processrequest")
            .addHeader("Authorization", "Bearer $accessToken")
            .post(payload.toString().toRequestBody(null))
            .build()

        return client.newCall(request).execute()
    }
}