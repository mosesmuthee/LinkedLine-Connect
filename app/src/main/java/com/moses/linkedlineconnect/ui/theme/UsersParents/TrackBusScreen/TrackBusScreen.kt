//package com.moses.linkedlineconnect.ui.theme.UsersParents.TrackBusScreen
//
//import android.Manifest
//import android.content.pm.PackageManager
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.model.*
//import com.google.maps.android.compose.*
//import com.google.type.LatLng
//
//@Composable
//fun TrackBusScreen(
//    busLocation: LatLng,
//    routePath: List<LatLng>,
//    onRefresh: () -> Unit
//) {
//    val context = LocalContext.current
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(busLocation, 15f)
//    }
//
//    // Check for location permissions
//    if (ActivityCompat.checkSelfPermission(
//            context,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED &&
//        ActivityCompat.checkSelfPermission(
//            context,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED
//    ) {
//        // TODO: Request location permissions
//    }
//
//    Scaffold(
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//            ) {
//                // Title
//                Text(
//                    text = "Track Bus",
//                    fontSize = 24.sp,
//                    color = Color(0xFF6200EE),
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//
//                // Google MapView
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                ) {
//                    GoogleMap(
//                        modifier = Modifier.fillMaxSize(),
//                        cameraPositionState = cameraPositionState,
//                        properties = MapProperties(isMyLocationEnabled = true),
//                        uiSettings = MapUiSettings(zoomControlsEnabled = true)
//                    ) {
//                        // Marker for Bus Location
//                        Marker(
//                            state = MarkerState(position = busLocation),
//                            title = "Bus Location",
//                            snippet = "Current location of the bus"
//                        )
//
//                        // Route Path Line
//                        Polyline(
//                            points = routePath,
//                            color = Color.Blue,
//                            width = 5f
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Refresh Button
//                Button(
//                    onClick = onRefresh,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
//                ) {
//                    Text("Refresh / Get ETA", fontSize = 16.sp, color = Color.White)
//                }
//            }
//        }
//    )
//}
//
//@Preview
//@Composable
//fun TrackBusScreenPreview() {
//    val sampleBusLocation = LatLng(-1.286389, 36.817223) // Example: Nairobi, Kenya
//    val sampleRoutePath = listOf(
//        LatLng(-1.286389, 36.817223),
//        LatLng(-1.292066, 36.821946),
//        LatLng(-1.300000, 36.830000)
//    )
//
//    TrackBusScreen(
//        busLocation = sampleBusLocation,
//        routePath = sampleRoutePath,
//        onRefresh = { /* TODO: Implement refresh logic */ }
//    )
//}
//
