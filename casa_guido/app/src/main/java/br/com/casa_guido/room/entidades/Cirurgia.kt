package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cirurgia")
data class Cirurgia(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pacienteId: Long,
    val data: String?, // ou Date
    val descricao: String?
)
