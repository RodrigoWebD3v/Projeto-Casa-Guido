package br.com.casa_guido.navigation.navHost

import Routes
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.screens.cadastro.CadastroScreen
import br.com.casa_guido.screens.main.Main


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navHostController: NavHostController, onLogout: () -> Unit, mensagemSucesso: Resultado) {
    NavHost(
            navController = navHostController,
            startDestination = Routes.DashBoardScreenRoute.route,
        ) {
        composable(Routes.DashBoardScreenRoute.route) {
            Main(
                navHostController = navHostController,
                onNavigateToLogin = {
                    onLogout()
                },
                mensagemSucesso = mensagemSucesso
            )
        }

        composable(
            Routes.CadastroScreenRoute.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )) { backStackEntry ->

            val userId = backStackEntry.arguments?.getString("userId")
            CadastroScreen(
                navHostController = navHostController,
                userId = userId
            )
        }
    }
}
