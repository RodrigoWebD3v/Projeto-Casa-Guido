package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "endereco")
data class Endereco(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val referencia: String?,
    val complemento: String?,
    val numero: String,
)

