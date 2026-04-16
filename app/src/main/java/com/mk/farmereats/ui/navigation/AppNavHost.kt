package com.mk.farmereats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mk.farmereats.ui.screens.onboarding.OnboardingScreen

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

        }
        composable("register"){

        }
        composable("main"){

        }
    }
}