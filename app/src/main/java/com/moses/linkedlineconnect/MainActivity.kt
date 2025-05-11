package com.moses.linkedlineconnect

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.moses.linkedlineconnect.navigation.AppNavHost
import com.moses.linkedlineconnect.ui.theme.LinkedLineConnectTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Optional edge-to-edge visuals

        setContent {
            LinkedLineConnectTheme {
                AppNavHost()
            }
        }
    }
}
