package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val data_nascimento: String,
    val cpf: String?,
    val identidade: String?,
    val naturalidade: String?,
    val genero: String?,
    val escolaridade: String?,
    val contato: String?,
    val estado_civil: String?,
    val situacao_profissional: Int?,
    val enderecoId: String? // UUID compatível com o backend
)