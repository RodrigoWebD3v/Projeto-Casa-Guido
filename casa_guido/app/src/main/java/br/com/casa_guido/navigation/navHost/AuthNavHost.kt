package br.com.casa_guido.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.navigation.authRoutes.AuthRoutes
import br.com.casa_guido.screens.login.Login


@Composable
fun AuthNavHost(navHostController: NavHostController, sucessoAutenticacao: (Resultado) -> Unit) {
    NavHost(
        navController = navHostController,
        startDestination = AuthRoutes.LoginRoute.route
    ) {
       composable(
           AuthRoutes.LoginRoute.route
       ){
           Login(onLoginSuccess = {
               sucessoAutenticacao(
                   Resultado.Sucesso("")
               )
           })
       }
    }
}