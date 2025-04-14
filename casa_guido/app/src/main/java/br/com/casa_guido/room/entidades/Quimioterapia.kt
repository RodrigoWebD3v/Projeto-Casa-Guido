package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "quimioterapia")
data class Quimioterapia(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val dataInicio: String?,
    val dataUltimaSessao: String?
)