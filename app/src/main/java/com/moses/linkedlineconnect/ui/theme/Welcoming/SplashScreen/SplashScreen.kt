package com.moses.linkedlineconnect.ui.theme.Welcoming.SplashScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.navigation.ROUTE_WELCOMEPAGE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var showLogo by remember { mutableStateOf(true) }
    var showWelcomeText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000) // Show logo for 2 seconds
        showLogo = false
        delay(250) // Delay before showing welcome text
        showWelcomeText = true
        delay(2500) // Wait for animation to finish
        navController.navigate(ROUTE_WELCOMEPAGE) // Navigate to the registration page
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
                painter = painterResource(id = R.drawable.icon), // Correct resource reference
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
                    text = "Where we connect your child to their destiny",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Powered by Smiley Creatives",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen(onSplashFinished = {})
//}