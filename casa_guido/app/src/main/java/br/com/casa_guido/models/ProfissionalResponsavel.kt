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