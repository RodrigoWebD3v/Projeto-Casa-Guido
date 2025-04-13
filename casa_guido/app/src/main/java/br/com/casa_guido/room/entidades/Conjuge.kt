package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conjuge")
data class Conjuge(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val responsavelId: Long,
    val pessoaId: Long
)
