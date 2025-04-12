package br.com.casa_guido.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.navigation.authRoutes.AuthRoutes
import br.com.casa_guido.screens.login.Login
import br.com.casa_guido.screens.splashScreen.SplashScreen


@Composable
fun AuthNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AuthRoutes.SplashScreenRoute
    ) {
        composable<AuthRoutes.SplashScreenRoute> {
            SplashScreen(
                onSuccessLogin = {
                    navHostController.navigate(
                        Routes.DashBoardScreenRoute
                    )
                },
                onLogin = {
                    navHostController.navigate(
                        AuthRoutes.LoginRoute
                    )
                }
            )
        }
       composable<AuthRoutes.LoginRoute> {
           Login(onLoginSuccess = {
               navHostController.navigate(
                   Routes.DashBoardScreenRoute
               )
           })
       }
    }
}