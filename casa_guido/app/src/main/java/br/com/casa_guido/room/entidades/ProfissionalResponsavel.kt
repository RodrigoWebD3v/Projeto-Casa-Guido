package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "profissional_responsavel")
data class ProfissionalResponsavel(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val tipo: String?,         // Ex: Médico, Psicólogo, etc.
    val nome: String,
    val crm: String?
)