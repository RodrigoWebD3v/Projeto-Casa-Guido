package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "responsavel")
data class Responsavel (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pessoaId: Long,
    val pacienteId: Long,
    val tipo_responsavel: String?,
    val estado_civil: Int?,
    val situacao_profissional: Int?,
    val salario: String?
)

