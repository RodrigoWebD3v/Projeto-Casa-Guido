package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Pessoa(
    val id: String = UUID.randomUUID().toString(),
    val nome: String = "",
    val telefone: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val rg: String = "",
    val endereco: Endereco = Endereco(),
    val situacaoProfissional: Int? = null,
    val estadoCivil: Int? = null,
    val naturalidade: String = "",
    val escolaridade: Int = 0,
    val serie: Int = 0,
    val salario: String = "",
    val cartaoSus: String = "",
    val respPrincipal: Int = 0,
    val profissao: String = "",
    val tipoEscola: Int? = null, // mantido como Int para enums
)