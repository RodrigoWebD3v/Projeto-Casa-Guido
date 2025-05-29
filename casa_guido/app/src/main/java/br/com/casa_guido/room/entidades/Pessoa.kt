package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val contato: String? = null, // Equivalente ao "telefone"
    val dataNascimento: String, // renomeado para camelCase, mantendo legibilidade
    val cpf: String? = null,
    val identidade: String? = null, // equivalente ao "rg"
    val naturalidade: String? = null,
    val genero: String? = null,
    val escolaridade: Int? = 0,
    val serie: Int? = 0,
    val estadoCivil: Int? = null, // mantido como Int para enums
    val situacaoProfissional: Int? = null, // idem
    val salario: String? = null, // manter como String, tratado como decimal no backend
    val enderecoId: String? = null, // UUID compatível com relação
    val telefone: String? = null,
    val cartaoSus: String? = null,
    val profissao: String? = null,
    val respPrincipal: Int?,
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