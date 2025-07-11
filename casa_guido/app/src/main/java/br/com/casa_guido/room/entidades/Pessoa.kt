package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val nome: String = "",
    val contato: String = "",
    val dataNascimento: String,
    val cpf: String = "",
    val identidade: String = "",
    val naturalidade: String = "",
    val genero: String = "",
    val escolaridade: Int = 0,
    val serie: Int = 0,
    val estadoCivil: Int? = null,
    val situacaoProfissional: Int? = null,
    val salario: String = "",
    val enderecoId: String = "",
    val telefone: String = "",
    val cartaoSus: String = "",
    val profissao: String = "",
    val respPrincipal: Int = 0,
)

/*
data class Pessoa(
    val id: String = UUID.randomUUID().toString(),
    val nome: String = "",
    val telefone: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val rg: String = "",
    val endereco: Endereco = Endereco(),
    val situacaoProfissional : Int = 0,
    val estadoCivil : Int = 0,
    val naturalidade: String = "",
    val escolaridade: String = "",
    val salario: String = "",
)

 */