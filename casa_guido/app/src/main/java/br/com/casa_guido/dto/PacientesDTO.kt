package br.com.casa_guido.dto

import br.com.casa_guido.models.Paciente
import br.com.casa_guido.models.PacienteRequestDTO
import kotlinx.serialization.Serializable


@Serializable
data class PacientesRequest(
    val paciente: PacienteRequestDTO,
    val token: String
)

@Serializable
data class PacientesResponse(
    val id: String,
    val nome: String
)

@Serializable
data class DataResponse(
    val data: PacientesResponse,
)