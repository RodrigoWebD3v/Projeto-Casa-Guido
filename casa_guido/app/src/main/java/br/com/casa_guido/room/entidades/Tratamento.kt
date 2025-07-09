package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tratamento")
data class Tratamento(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String = "",
    val tipo: String = "",
    val data_inicio: String = "", // ou Date
    val data_ultima_sessao: String = "", // ou Date
    val observacoes: String = "",
    val outros_tratamentos : String = ""
)
