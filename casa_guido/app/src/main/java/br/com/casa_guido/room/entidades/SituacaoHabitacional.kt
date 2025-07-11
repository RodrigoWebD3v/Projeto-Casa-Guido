package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "situacao_habitacional")
data class SituacaoHabitacional(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String = "", //usado
    val comoAdquiriuCasa: Int? = null, //usado
    val area: Int? = null,
    val meioDeTransporte: Int? = null,
    val meioDeComunicao: Int? = null,
    val possuiBanheiros: Int? = null,
    val numeroComodos: String = "",
    val material: Int? = null,
    val bens: Array<Int> = emptyArray(),
    val dentroDeCasa: Int? = null
)
