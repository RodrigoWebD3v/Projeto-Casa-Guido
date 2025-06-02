package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SituacaoHabitacional(
    val id: String = UUID.randomUUID().toString(),
    val comoAdquiriuCasa: Int? = null,
    val compPropriedade: Int? = null,
    val area: Int? = null,
    val numeroComodos: String? = null,
    val material: Int? = null,
    val eletrodomesticos: Array<Int> = emptyArray(),
    val bens: Array<Int> = emptyArray(),
    val meioDeTransporte: Int? = null,
    val meioDeComunicao: Int? = null,
    val possuiBanheiros: Int? = null,
    val dentroDeCasa: Int? = null,
    val destinoDoLixo: Int? = null,
    val agua: Int? = null,
    val valorTotal: String? = null,
)