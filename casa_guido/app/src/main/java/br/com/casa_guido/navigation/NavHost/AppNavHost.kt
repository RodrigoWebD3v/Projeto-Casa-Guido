package br.com.casa_guido.navigation.NavHost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.navigation.routes.Routes
import br.com.casa_guido.screens.home.Home


@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navHostController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            Home(
                paddingValues = paddingValues
            )
        }
    }

}