package br.com.casa_guido.navigation.authRoutes

sealed class AuthRoutes (
    val route: String
){
    data object LoginRoute: AuthRoutes(route = "login")

    data object SplashScreenRoute: AuthRoutes(route = "splash")
}