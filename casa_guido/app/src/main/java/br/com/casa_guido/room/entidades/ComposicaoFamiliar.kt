package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "composicao_familiar")
data class ComposicaoFamiliar(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val nome: String?,
    val parentesco: String?,
    val data_nascimento: String?,
    val escolaridade: String?,
    val trabalha: Boolean?,
    val renda: String?
)
