package br.com.casa_guido.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object DashBoard: Routes()

    @Serializable
    data object Pacientes: Routes()

    @Serializable
    data object Agenda: Routes()

    @Serializable
    data object Configuracoes: Routes()

}