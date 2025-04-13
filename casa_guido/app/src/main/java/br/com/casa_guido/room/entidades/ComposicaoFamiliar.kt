package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "composicao_familiar")
data class ComposicaoFamiliar(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pacienteId: Long,
    val nome: String?,
    val parentesco: String?,
    val data_nascimento: String?,
    val escolaridade: String?,
    val trabalha: Boolean?,
    val renda: String?
)
