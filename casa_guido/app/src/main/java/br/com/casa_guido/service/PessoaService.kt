package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.repository.PessoaRepository
import br.com.casa_guido.screens.Endereco
import br.com.casa_guido.screens.Pessoa
import br.com.casa_guido.room.entidades.Pessoa as PessoaEntidade
import br.com.casa_guido.screens.Pessoa as PessoaUI


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
            estadoCivil = this.estadoCivil,
            situacaoProfissional = this.situacaoProfissional,
            salario = this.salario,
            enderecoId = enderecoId,
            cartaoSus = this.cartaoSus,
        )
    }

    private suspend fun PessoaEntidade.toUI(): PessoaUI {
        return PessoaUI(
            id = this.id,
            nome = this.nome,
            dataNascimento = this.dataNascimento,
            cpf = this.cpf!!,
            rg = this.identidade!!,
            telefone = this.telefone!!,
            naturalidade = this.naturalidade!!,
            escolaridade = this.escolaridade!!,
            estadoCivil = this.estadoCivil?.let { this.estadoCivil ?: 0 },
            situacaoProfissional = this.situacaoProfissional.let { this.situacaoProfissional ?: 0 },
            salario = this.salario!!,
            endereco = this.enderecoId?.let { enderecoService.getById(it) } ?: Endereco(),
            cartaoSus = this.cartaoSus!!,
        )
    }

}
