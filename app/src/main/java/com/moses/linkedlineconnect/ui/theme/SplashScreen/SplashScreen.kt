package com.moses.linkedlineconnect.ui.theme.SplashScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moses.linkedlineconnect.R // Correct import for your app's resources
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    var showLogo by remember { mutableStateOf(true) }
    var showWelcomeText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000) // Show logo for 2 seconds
        showLogo = false
        delay(500) // Delay before showing welcome text
        showWelcomeText = true
        delay(2000) // Wait for animation to finish
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Logo Animation
        AnimatedVisibility(
            visible = showLogo,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Image(
                painter = painterResource(id = R.drawable.moses), // Correct resource reference
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
        }

        // Welcome Animation
        AnimatedVisibility(
            visible = showWelcomeText,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to LinkedLine Connect",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Powered by LinkedLine Connect",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(onSplashFinished = {})
}