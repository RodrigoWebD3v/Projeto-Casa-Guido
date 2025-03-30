package br.com.casa_guido.navigation.NavHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.screens.login.Login
import br.com.casa_guido.navigation.authRoutes.AuthRoutes


@Composable
fun AuthNavHost(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = AuthRoutes.LoginRoute
    ) {
       composable<AuthRoutes.LoginRoute> {
           Login()
       }
    }

}