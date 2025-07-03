package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Radio(
    val dataInicio: String = "",
    val dataUltimaSessao: String = "",
    val id : String = UUID.randomUUID().toString()
)

@Serializable
data class RadioDTO(
    val dataInicio: String = "",
    val dataUltimaSessao: String = "",
)

fun Radio.toRequestDTO(): RadioDTO {
    return RadioDTO(
        dataInicio = this.dataInicio,
        dataUltimaSessao = this.dataUltimaSessao
    )
}