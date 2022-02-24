package com.example.registrationzkb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.registrationzkb.screens.registration.RegistrationScreen
import com.example.registrationzkb.screens.registration.RegistrationViewModel
import com.example.registrationzkb.screens.success.SuccessScreen
import com.example.registrationzkb.screens.success.SuccessViewModel
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
            val backStackEntry = navController.currentBackStackEntryAsState()
            //val currentScreen = AppScreens.fromRoute(backStackEntry.value?.destination?.route)

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
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(
                registrationViewModel = registrationViewModel,
                validationSuccess = {
                    navHostController.navigate(route = AppScreens.Success.name)
                }
            )
        }

        composable(AppScreens.Success.name) {
            val successViewModel = hiltViewModel<SuccessViewModel>()
            SuccessScreen(successViewModel = successViewModel)
        }

    }
}