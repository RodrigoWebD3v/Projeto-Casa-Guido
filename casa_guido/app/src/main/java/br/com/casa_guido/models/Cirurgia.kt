package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Cirurgia(
    val nome: String = "",
    val data: String = "",
    val id : String = UUID.randomUUID().toString(),
    val cid: String = "",
)

@Serializable
data class CirurgiaDTO(
    val nome: String = "",
    val data: String = "",
    val cid: String = "",
)

fun Cirurgia.toRequestDTO(): CirurgiaDTO {
    return CirurgiaDTO(
        nome = this.nome,
        data = this.data,
        cid = this.cid
    )
}