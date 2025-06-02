package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Quimio(
    val id: String = UUID.randomUUID().toString(),
    val dataInicio: String = "",
    val dataUltimaSessao: String = "",
)