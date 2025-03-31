package br.com.casa_guido.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object Home: Routes()

}