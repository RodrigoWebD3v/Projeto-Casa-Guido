package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "endereco")
data class Endereco(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val complemento: String = "",
    val unidade: String = "",
    val bairro: String = "",
    val estado: String = "",
    val uf: String = "",
    val regiao: String = "",
    val localidade: String = "",
    val referencia: String = "",
    val pais: String = "Brasil"
)

