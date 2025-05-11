//package com.moses.linkedlineconnect.data
//
//import android.app.ProgressDialog
//import android.content.Context
//import android.os.Build
//import android.util.Log
//import android.widget.Toast
//import androidx.annotation.RequiresApi
//import androidx.biometric.BiometricManager
//import androidx.biometric.BiometricPrompt
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.FragmentActivity
//
//class BiometricAuthHelper(private val activity: FragmentActivity) {
//    private val context: Context = activity.applicationContext
//
//    @RequiresApi(Build.VERSION_CODES.R)
//    fun biometricLogin(onSuccess: () -> Unit, onFailure: () -> Unit) {
//        try {
//            val biometricManager = BiometricManager.from(context)
//            val canAuthenticate = biometricManager.canAuthenticate(
//                BiometricManager.Authenticators.BIOMETRIC_STRONG or
//                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
//            )
//
//            if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
//                val message = when (canAuthenticate) {
//                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> "No biometric hardware found"
//                    BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> "Biometric hardware unavailable"
//                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> "No biometric credentials enrolled"
//                    else -> "Biometric authentication not supported"
//                }
//                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                onFailure()
//                return
//            }
//
//            val executor = ContextCompat.getMainExecutor(activity)
//            val biometricPrompt = BiometricPrompt(
//                activity,
//                executor,
//                object : BiometricPrompt.AuthenticationCallback() {
//                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                        super.onAuthenticationSucceeded(result)
//                        Toast.makeText(context, "Biometric Authentication Successful", Toast.LENGTH_LONG).show()
//                        onSuccess()
//                    }
//
//                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                        super.onAuthenticationError(errorCode, errString)
//                        Toast.makeText(context, "Authentication Error: $errString", Toast.LENGTH_LONG).show()
//                        Log.e("BiometricAuthHelper", "Authentication Error: $errString")
//                        onFailure()
//                    }
//
//                    override fun onAuthenticationFailed() {
//                        super.onAuthenticationFailed()
//                        Toast.makeText(context, "Authentication Failed", Toast.LENGTH_LONG).show()
//                        Log.w("BiometricAuthHelper", "Authentication Failed")
//                        onFailure()
//                    }
//                }
//            )
//
//            val promptInfo = BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric Login")
//                .setSubtitle("Use your fingerprint or face to log in")
//                .setNegativeButtonText("Cancel")
//                .build()
//
//            biometricPrompt.authenticate(promptInfo)
//
//        } catch (e: Exception) {
//            Toast.makeText(context, "An error occurred: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
//            Log.e("BiometricAuthHelper", "Exception during biometric login", e)
//            onFailure()
//        }
//    }
//}
