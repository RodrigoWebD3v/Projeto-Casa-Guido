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
    val situacaoProfissional: Int = 0,
    val estadoCivil: Int = 0,
    val naturalidade: String = "",
    val escolaridade: Int = 0,
    val serie: Int = 0,
    val salario: String = "",
    val cartaoSus: String = "",
    val respPrincipal: Int = 0,
    val profissao: String = "",
    val tipoEscola: Int = 0, // mantido como Int para enums
)

@Serializable
data class PessoaDTO(
    val nome: String = "",
    val telefone: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val rg: String = "",
    val endereco: EnderecoDTO,
    val situacaoProfissional: Int = 0,
    val estadoCivil: Int = 0,
    val naturalidade: String = "",
    val escolaridade: Int = 0,
    val serie: Int = 0,
    val salario: String = "",
    val cartaoSus: String = "",
    val respPrincipal: Int = 0,
    val profissao: String = "",
    val tipoEscola: Int = 0,
)

fun Pessoa.toRequestDTO(): PessoaDTO {
    return PessoaDTO(
        nome = this.nome,
        telefone = this.telefone,
        dataNascimento = this.dataNascimento,
        cpf = this.cpf,
        rg = this.rg,
        endereco = this.endereco.toRequestDTO(),
        situacaoProfissional = this.situacaoProfissional,
        estadoCivil = this.estadoCivil,
        naturalidade = this.naturalidade,
        escolaridade = this.escolaridade,
        serie = this.serie,
        salario = this.salario,
        cartaoSus = this.cartaoSus,
        respPrincipal = this.respPrincipal,
        profissao = this.profissao,
        tipoEscola = this.tipoEscola
    )
}