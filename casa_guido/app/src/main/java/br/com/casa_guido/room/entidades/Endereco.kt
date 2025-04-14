package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "endereco")
data class Endereco(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String, // manter como está, mas aplicar validação no app para 2 caracteres
    val referencia: String?,
    val complemento: String?,
    val numero: String
)