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

@Serializable
data class SituacaoHabitacionalDTO(
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

fun SituacaoHabitacional.toRequestDTO(): SituacaoHabitacionalDTO {
    return SituacaoHabitacionalDTO(
        comoAdquiriuCasa = this.comoAdquiriuCasa,
        area = this.area,
        numeroComodos = this.numeroComodos,
        material = this.material,
        bens = this.bens,
        meioDeTransporte = this.meioDeTransporte,
        meioDeComunicao = this.meioDeComunicao,
        possuiBanheiros = this.possuiBanheiros,
        dentroDeCasa = this.dentroDeCasa
    )
}