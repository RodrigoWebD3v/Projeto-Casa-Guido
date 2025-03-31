package br.com.casa_guido.navigation

import kotlinx.serialization.Serializable

sealed class RootRoutes {

    @Serializable
    data object Auth : RootRoutes()

    @Serializable
    data object App : RootRoutes()
}
