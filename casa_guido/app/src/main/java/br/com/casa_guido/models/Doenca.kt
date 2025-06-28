package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Doenca(
    val nome: String = "",
    val id : String = UUID.randomUUID().toString()
)

@Serializable
data class DoencaDTO(
    val nome: String = "",
)

fun Doenca.toRequestDTO(): DoencaDTO {
    return DoencaDTO(
        nome = this.nome
    )
}