package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SituacaoHabitacional(
    val id: String = UUID.randomUUID().toString(),
    val comoAdquiriuCasa: Int = 0,
    val area: Int = 0,
    val numeroComodos: Int = 0,
    val material: Int = 0,
    val bens: Array<Int> = emptyArray(),
    val meioDeTransporte: Int = 0,
    val meioDeComunicao: Int = 0,
    val possuiBanheiros: Int = 0,
    val dentroDeCasa: Int = 0,
)

@Serializable
data class SituacaoHabitacionalDTO(
    val comoAdquiriuCasa: Int = 0,
    val area: Int? = 0,
    val numeroComodos: Int = 0,
    val material: Int = 0,
    val bens: Array<Int> = emptyArray(),
    val meioDeTransporte: Int = 0,
    val meioDeComunicao: Int  = 0,
    val possuiBanheiros: Int  = 0,
    val dentroDeCasa: Int = 0,
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