package br.com.casa_guido.navigation.navHost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.navigation.authRoutes.AuthRoutes
import br.com.casa_guido.screens.login.Login


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthNavHost(
    navHostController: NavHostController,
    sucessoAutenticacao: (Resultado) -> Unit,
    resultado: Resultado
) {
    NavHost(
        navController = navHostController,
        startDestination = AuthRoutes.LoginRoute.route
    ) {
       composable(
           AuthRoutes.LoginRoute.route
       ){
           Login(onLoginSuccess = {
               sucessoAutenticacao(
                   Resultado.Sucesso("Login realizado com sucesso")
               )
           },
               resultado = resultado
           )

       }
    }
}