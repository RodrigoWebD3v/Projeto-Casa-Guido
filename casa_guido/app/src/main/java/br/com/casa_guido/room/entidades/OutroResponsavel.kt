package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outro_responsavel")
data class OutroResponsavel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pacienteId: Long,
    val pessoaId: Long,
    val tipo_vinculo: String?
)
