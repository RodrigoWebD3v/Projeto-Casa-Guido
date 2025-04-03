package br.com.casa_guido.navigation.NavHost

import Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Groups
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.screens.cadastro.CadastroScreen
import br.com.casa_guido.screens.home.HomeScreen
import br.com.casa_guido.screens.pacientes.PacientesScreens


data class itemNavBar(
    val texto: String,
    val icon: ImageVector,
    val rota: String
)

@Composable
fun AppNavHost(navHostController: NavHostController) {

        NavHost(
            navController = navHostController,
            startDestination = Routes.DashBoardScreenRoute.route,
        ) {
            composable(Routes.DashBoardScreenRoute.route) {
                HomeScreen(

                    onAgenda = {
                        navHostController.navigate(Routes.AgendaScreenRoute.route) {
                            launchSingleTop = true
                        }
                    },

                    onPacientes = {
                        navHostController.navigate(Routes.PacientesScreenRoute.route)
                        {
                            popUpTo(Routes.PacientesScreenRoute.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },

                    selectedItemParam = itemNavBar(
                        texto = "Dashboard",
                        icon = Icons.Default.Dashboard,
                        rota = Routes.DashBoardScreenRoute.route
                    )
                )
            }

            composable(route = Routes.PacientesScreenRoute.route) {
                PacientesScreens(
                    onNavigate = {
                        navHostController.navigate(it)
                    },
                    selectedItemParam = itemNavBar(
                        texto = "Pacientes",
                        icon = Icons.Default.Groups,
                        rota = Routes.PacientesScreenRoute.route
                    )
                )
            }

            composable(Routes.CadastroScreenRoute.route) {
                CadastroScreen() {
                    navHostController.navigate(it)
                }
            }
        }

}