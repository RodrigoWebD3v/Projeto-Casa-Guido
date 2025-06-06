package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Cras(
    val municipio: String = "",
    val bairro: String = ""
)