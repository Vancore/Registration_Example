package com.example.registrationzkb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registrationzkb.screens.registration.RegistrationScreen
import com.example.registrationzkb.screens.success.SuccessScreen
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RegistrationApp()
        }
    }
}

@Composable
fun RegistrationApp() {
    RegistrationZKBTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
            }

            val navController = rememberNavController()

            RegistrationNavHost(navHostController = navController)
        }
    }
}

@Composable
fun RegistrationNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AppScreens.RegistrationForm.name
    ) {

        composable(AppScreens.RegistrationForm.name) {
            RegistrationScreen { navHostController.navigate(route = AppScreens.Success.name) }
        }

        composable(AppScreens.Success.name) {
            SuccessScreen()
        }

    }
}