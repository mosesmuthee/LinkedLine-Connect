package com.moses.linkedlineconnect.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moses.linkedlineconnect.data.AuthViewModel
import com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminChatPage.AdminChatScreen
import com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminDashboard.AdminDashboardScreen
import com.moses.linkedlineconnect.ui.theme.BusPickupScreen.BusPickupDialog
import com.moses.linkedlineconnect.ui.theme.EscortDashboard.EscortDashboardScreen
import com.moses.linkedlineconnect.ui.theme.PaymentConfirmationScreen.PayConfirmDialog
import com.moses.linkedlineconnect.ui.theme.PaymentScreen.MpesaPaymentScreen
import com.moses.linkedlineconnect.ui.theme.Registration.UnifiedRegistrationScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.BookingPage.BookingPageScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.ChatPageScreen.ChatPageScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.DashBoardScreen.DashBoardScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.LoginScreen.LoginScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.ParentProfile.ParentProfileScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.StudentDetailsPage.StudentDetailsPageScreen
import com.moses.linkedlineconnect.ui.theme.UsersParents.StudentRegScreen.StudentRegScreen
import com.moses.linkedlineconnect.ui.theme.Welcoming.SplashScreen.SplashScreen
import com.moses.linkedlineconnect.viewmodel.AppViewModel


//import com.moses.linkedlineconnect.ui.theme.Welcoming.WelcomePage.WelcomePageScreenPreview




@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASHSCREEN,
    viewModel: AppViewModel = viewModel(),
    authViewModel: AuthViewModel = AuthViewModel(navController, LocalContext.current.applicationContext) // Proper ViewModel instantiation
) {
    val context = LocalContext.current // Use LocalContext correctly
    val students = viewModel.students.collectAsState(initial = emptyList())
    val schools = viewModel.schools.collectAsState(initial = emptyList())
    val routes = viewModel.routes.collectAsState(initial = emptyList())

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable(ROUTE_WELCOMEPAGE) {
            UnifiedRegistrationScreen(
                navController = navController,
                viewModel = viewModel, // Pass the viewModel here
               authviewModel = authViewModel,
                onRegister = { email, formData, imageUri ->
                    authViewModel.signup(
                        firstname = formData["firstname"] ?: "",
                        lastname = formData["lastname"] ?: "",
                        email = email,
                        idNumber = formData["idNumber"] ?: "",
                        phoneNumber = formData["phoneNumber"] ?: "",
                        password = formData["password"] ?: "",
                        confirmPassword = formData["confirmPassword"] ?: "",
                        role = formData["role"] ?: "Parent",
                        age = formData["age"] ?: "",
                    )
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
composable(ROUTE_SPLASHSCREEN){
    SplashScreen(navController = navController)
}
        composable(ROUTE_BOOKINGPAGE) {
            BookingPageScreen(
                navController = navController,
                students = students.value,
                schools = schools.value,
                routes = routes.value
            )
        }
        
        composable (ROUTE_CHATPAGE){
            ChatPageScreen()
        }
        composable (ROUTE_ADMINASSIGNBUS){
            Text("Coming Soon, please bear with us")
        }
        composable (ROUTE_STUDENTDETAILS){
            StudentDetailsPageScreen(
                onDownloadDetails = {}
            )
        }
        composable (ROUTE_SCHOOLROUTEREGISTER){
            Text("Coming Soon")
        }
        composable (ROUTE_REGISTEREDSTUDENTS){
            Text("Coming Soon")

        }
        composable (ROUTE_PAYMENTSCREEN){
            MpesaPaymentScreen(
                navController.toString(),
                navController = navController,
                authViewModel = authViewModel)
//                studentId = "studentId", // Replace with actual student ID
//                onPay = { paymentDetails ->
//                    // Handle payment logic here
//                }
//            )
        }
        composable(ROUTE_PARENTPROFILE) {
            ParentProfileScreen(
                navController = navController,
//                parentId = String()
//                onNavigateBack = { navController.popBackStack() },
//                onLogout = { authViewModel.logout() },
//                onNavigateToDashboard = { navController.navigate(ROUTE_DASHBOARDParent) },
//                onNavigateToBusPickup = { navController.navigate(ROUTE_BUSPICKUP) },
//                onNavigateToChatPage = { navController.navigate(ROUTE_CHATPAGE) },
//                onNavigateToPaymentConfirmation = { navController.navigate(ROUTE_PAYMENTCONFIRMATION) },
//                onNavigateToPayment = { navController.navigate(ROUTE_PAYMENTSCREEN) },
//                parentId = TODO()
            )
        }
        composable(
            route = "$ROUTE_PAYMENTCONFIRMATION/{studentName}/{schoolName}",
            arguments = listOf(
                navArgument("studentName") { type = NavType.StringType },
                navArgument("schoolName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val studentName = backStackEntry.arguments?.getString("studentName") ?: ""
            val schoolName = backStackEntry.arguments?.getString("schoolName") ?: ""
            PayConfirmDialog(
                studentName = studentName,
                schoolName = schoolName,
                onDismiss = { /* handle dismiss */ },
                navController = navController
            )
        }
//        composable (ROUTE_ESCORTLOGIN){
//            EscortLoginScreen(
//                navController = navController,
//                onLogin = { email, password ->
//                    // Handle login logic here
//                },
//                onNavigateToWelcome = {
//                    // Handle navigation to the welcoming page
//                }
//            )
//        }
        composable (ROUTE_LOGINParent){
            LoginScreen(
                navController = navController,
                onNavigateToSignUp = {navController.navigate(ROUTE_REGISTERParent) },
                onForgotPassword = { /* Handle forgot password */ }
            )
        }
//        composable (ROUTE_ESCORTREGISTER){
//            EscortRegScreen(
//                onRegister = { escort ->
//                    // Handle registration logic here
//                },
//                onNavigateBack = { /* Handle navigation back */ }
//            )
//        }

        composable(ROUTE_DASHBOARDParent) {
            DashBoardScreen(
                navController = navController,
                authViewModel = authViewModel // Pass authViewModel
            )
        }
        composable (ROUTE_BUSPICKUP){
            BusPickupDialog(
                studentId = "studentId", // Replace with actual student ID
                parentId = "parentId", // Replace with actual parent ID
                onDismiss = { /* Handle dismiss */ },
                onUpdateStatus = { isPickedUp, isDroppedOff ->
                    // Handle update status logic here
                }
            )
        }
        composable (ROUTE_DASHBOARDEscort){
            EscortDashboardScreen(
                navController = navController,
                authviewModel = authViewModel,
                onSendNotification = { message ->
                    // Handle the notification logic here
                    println("Notification sent: $message")
                }
            )

        }
        composable(ROUTE_ADMIN_DASHBOARD) {
              AdminDashboardScreen(
                  navController = navController,
                  authViewModel = authViewModel
              )
        }
        composable (ROUTE_STUDENTREG){
            StudentRegScreen(navController)
        }
        composable (ROUTE_ADMINCHATPAGE){
            AdminChatScreen(navController)
        }

        }

    }