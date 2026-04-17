package com.mk.farmereats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mk.farmereats.ui.screens.login.LoginScreen
import com.mk.farmereats.ui.screens.onboarding.OnboardingScreen
import com.mk.farmereats.ui.screens.onboarding.RegisterCompletedScreen
import com.mk.farmereats.ui.screens.register.RegisterScreen

@Composable
fun AppNavHost(isLoggedIn: Boolean){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "main" else "onboarding"
    ){
        composable("onboarding"){
            OnboardingScreen(onLoginClick = {
                navController.navigate("login"){
                    popUpTo("onboarding"){ inclusive = true}
                }
             },
                onJoinClick = {
                    navController.navigate("register"){
                        popUpTo("onboarding"){ inclusive = true}
                    }
                }
            )
        }

        composable("login"){
            LoginScreen(
                onCreateAccountClick = {
                    navController.navigate("register"){
                        popUpTo("login"){ inclusive = true}
                    }
                }
            )
        }
        composable("register"){
            RegisterScreen(
                goBackToLogin = {
                    navController.navigate("login"){
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
        composable("main"){

        }
        composable("registerCompleted"){
            RegisterCompletedScreen(

            )
        }
    }
}