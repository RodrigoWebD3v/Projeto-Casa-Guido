package br.com.casa_guido.navigation.authRoutes

import kotlinx.serialization.Serializable

sealed class AuthRoutes {

    @Serializable
    data object LoginRoute: AuthRoutes()


    @Serializable
    data object SplashScreenRoute: AuthRoutes()
}