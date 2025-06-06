package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "situacao_habitacional")
data class SituacaoHabitacional(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String?, //usado
    val comoAdquiriuCasa: Int?, //usado
    val area: Int?,//usado
    val meioDeTransporte: Int?,
    val meioDeComunicao: Int?,
    val possuiBanheiros: Int?,
    val numeroComodos: Int?,
    val material: Int?,
    val bens: Array<Int>?,
    val dentroDeCasa: Int?
)
