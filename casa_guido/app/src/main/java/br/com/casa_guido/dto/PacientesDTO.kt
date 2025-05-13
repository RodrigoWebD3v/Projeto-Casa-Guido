package br.com.casa_guido.dto

import br.com.casa_guido.screens.Paciente
import kotlinx.serialization.Serializable


@Serializable
data class PacientesRequest(
    val paciente: Paciente,
)

@Serializable
data class PacientesResponse(
    val status: String,
)