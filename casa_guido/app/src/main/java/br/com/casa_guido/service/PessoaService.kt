package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.repository.PessoaRepository
import br.com.casa_guido.models.Endereco
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.room.entidades.Pessoa as PessoaEntidade
import br.com.casa_guido.models.Pessoa as PessoaUI


class PessoaService(
    private val pessoaRepository: PessoaRepository,
    private val enderecoService: EnderecoService,
) {

    val pessoas = pessoaRepository.pessoas

    suspend fun getById(id: String): PessoaUI? {
        return pessoaRepository.getById(id)?.toUI()
    }

    suspend fun createPessoa(pessoa: PessoaUI, endereco: Endereco) {
        Log.i(
            "PessoaService",
            "createPessoa: ${pessoa.situacaoProfissional} - ${pessoa.estadoCivil}"
        )
        pessoaRepository.insert(
            pessoa.toEntidade(
                enderecoId = endereco.id
            )
        )
    }

    private fun Pessoa.toEntidade(
        enderecoId: String
    ): PessoaEntidade {
        Log.i(
            "PessoaService",
            "toEntidade: ${this.escolaridade} - ${this.serie}"
        )

        return PessoaEntidade(
            id = this.id,
            nome = this.nome,
            dataNascimento = this.dataNascimento,
            cpf = this.cpf,
            identidade = this.rg,
            telefone = this.telefone,
            naturalidade = this.naturalidade,
            genero = "",
            escolaridade = this.escolaridade,
            serie = this.serie,
            estadoCivil = this.estadoCivil,
            situacaoProfissional = this.situacaoProfissional,
            salario = this.salario,
            enderecoId = enderecoId,
            cartaoSus = this.cartaoSus,
            profissao = this.profissao,
            respPrincipal = this.respPrincipal
        )
    }

    private suspend fun PessoaEntidade.toUI(): PessoaUI {
        return PessoaUI(
            id = this.id,
            nome = this.nome,
            dataNascimento = this.dataNascimento,
            cpf = this.cpf?.let { this.cpf } ?: "",
            rg = this.identidade?.let { this.identidade } ?: "",
            telefone = this.telefone?.let { this.telefone } ?: "",
            naturalidade = this.naturalidade?.let { this.naturalidade } ?: "",
            escolaridade = this.escolaridade.let { this.escolaridade },
            estadoCivil = this.estadoCivil?.let { this.estadoCivil ?: 0 },
            situacaoProfissional = this.situacaoProfissional.let { this.situacaoProfissional ?: 0 },
            salario = this.salario?.let { this.salario } ?: "",
            endereco = this.enderecoId?.let { enderecoService.getById(it) } ?: Endereco(),
            cartaoSus = this.cartaoSus?.let { this.cartaoSus } ?: "",
            serie = this.serie.let { this.serie },
            profissao = this.profissao?.let { this.profissao } ?: "",
            respPrincipal = this.respPrincipal?.let { this.respPrincipal } ?: 0,
        )
    }

}
