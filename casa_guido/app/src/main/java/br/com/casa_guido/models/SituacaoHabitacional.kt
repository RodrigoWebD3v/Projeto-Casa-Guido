package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SituacaoHabitacional(
    val id: String = UUID.randomUUID().toString(),
    val comoAdquiriuCasa: Int? = null,
    val area: Int? = null,
    val numeroComodos: Int? = null,
    val material: Int? = null,
    val bens: Array<Int> = emptyArray(),
    val meioDeTransporte: Int? = null,
    val meioDeComunicao: Int? = null,
    val possuiBanheiros: Int? = null,
    val dentroDeCasa: Int? = null,
)