package br.com.casa_guido.navigation.NavHost

import Routes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.screens.cadastro.CadastroScreen
import br.com.casa_guido.screens.main.Main


data class itemNavBar(
    val texto: String,
    val icon: ImageVector,
    val id: Int
)

@Composable
fun AppNavHost(navHostController: NavHostController) {


    NavHost(
            navController = navHostController,
            startDestination = Routes.DashBoardScreenRoute.route,
        ) {
        composable(Routes.DashBoardScreenRoute.route) {
            Main(
                navHostController = navHostController,
            )
        }

        composable(Routes.CadastroScreenRoute.route) {
            CadastroScreen(
                navHostController = navHostController,
            )
        }
    }
}
