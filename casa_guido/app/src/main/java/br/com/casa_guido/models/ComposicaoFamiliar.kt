package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ComposicaoFamiliar(
    val nome: String = "",
    val parentesco: String = "",
    val dataNascimento: String = "",
    val idade: Int = 0,
    val estudaOpcional: Int? = null,
    val serie: String = "",
    val trabalhaOpcional: Int? = null,
    val renda: String = "",
    val id: String = UUID.randomUUID().toString()
)