package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tratamento")
data class Tratamento(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pacienteId: Long,
    val tipo: String?,
    val data_inicio: String?, // ou Date
    val data_ultima_sessao: String?, // ou Date
    val observacoes: String?
)

