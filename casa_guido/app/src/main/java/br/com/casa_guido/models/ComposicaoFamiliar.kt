package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class ComposicaoFamiliar(
    @Transient
    val id: String = UUID.randomUUID().toString(),
    val nome: String = "",
    val parentesco: String = "",
    val dataNascimento: String = "",
    val idade: Int = 0,
    val estudaOpcional: Int = 0,
    val serie: String = "",
    val trabalhaOpcional: Int = 0,
    val renda: String = "",
)

@Serializable
data class ComposicaoFamiliarDTO(
    val nome: String = "",
    val parentesco: String = "",
    val dataNascimento: String = "",
    val idade: Int = 0,
    val estudaOpcional: Int = 0,
    val serie: String = "",
    val trabalhaOpcional: Int = 0,
    val renda: String = "",
)

fun ComposicaoFamiliar.toRequestDTO(): ComposicaoFamiliarDTO {
    return ComposicaoFamiliarDTO(
        nome = this.nome,
        parentesco = this.parentesco,
        dataNascimento = this.dataNascimento,
        idade = this.idade,
        estudaOpcional = this.estudaOpcional,
        serie = this.serie,
        trabalhaOpcional = this.trabalhaOpcional,
        renda = this.renda
    )
}