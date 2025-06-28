package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProfissionalResponsavel(
    val tipo: String = "",
    val nome: String = "",
    val crm: String = "",
    val id : String = UUID.randomUUID().toString()
)

@Serializable
data class ProfissionalResponsavelDTO(
    val tipo: String = "",
    val nome: String = "",
    val crm: String = "",
    val id : String = UUID.randomUUID().toString()
)

fun ProfissionalResponsavel.toRequestDTO(): ProfissionalResponsavelDTO {
    return ProfissionalResponsavelDTO(
        tipo = this.tipo,
        nome = this.nome,
        crm = this.crm,
        id = this.id
    )
}