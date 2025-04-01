package br.com.casa_guido.navigation.NavHost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.navigation.authRoutes.AuthRoutes
import br.com.casa_guido.navigation.routes.Routes
import br.com.casa_guido.screens.login.Login


@Composable
fun AuthNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navHostController,
        startDestination = AuthRoutes.LoginRoute
    ) {
       composable<AuthRoutes.LoginRoute> {
           Login(paddingValues = paddingValues, onLoginSuccess = {
               navHostController.navigate(
                   Routes.DashBoard
               )
           })
       }
    }
}