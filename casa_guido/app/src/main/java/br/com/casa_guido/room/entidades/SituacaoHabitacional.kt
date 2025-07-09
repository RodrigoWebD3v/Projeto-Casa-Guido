package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "situacao_habitacional")
data class SituacaoHabitacional(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String = "", //usado
    val comoAdquiriuCasa: Int = 0, //usado
    val area: Int = 0,//usado
    val meioDeTransporte: Int = 0,
    val meioDeComunicao: Int = 0,
    val possuiBanheiros: Int = 0,
    val numeroComodos: Int = 0,
    val material: Int = 0,
    val bens: Array<Int> = emptyArray(),
    val dentroDeCasa: Int = 0
)
