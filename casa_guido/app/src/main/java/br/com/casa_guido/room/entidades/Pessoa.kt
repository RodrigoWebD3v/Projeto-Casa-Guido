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
    val identidade: String = "", // equivalente ao "rg"
    val naturalidade: String = "",
    val genero: String = "",
    val escolaridade: Int = 0,
    val serie: Int = 0,
    val estadoCivil: Int = 0, // mantido como Int para enums
    val situacaoProfissional: Int = 0, // idem
    val salario: String = "", // manter como String, tratado como decimal no backend
    val enderecoId: String = "", // UUID compatível com relação
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