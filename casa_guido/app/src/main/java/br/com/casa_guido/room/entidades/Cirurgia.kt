package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "cirurgia")
data class Cirurgia(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    val pacienteId: String,

    val nome: String = "",
    val data: String = "",
    val descricao: String = "",
    val cid: String = "",
)
