package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val data_nascimento: String, // ou Date com TypeConverter
    val cpf: String?,
    val identidade: String?,
    val naturalidade: String?,
    val genero: String?,
    val escolaridade: String?,
    val contato: String?,
    val enderecoId: Long? // FK para EnderecoEntity
)