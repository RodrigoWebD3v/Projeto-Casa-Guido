package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.repository.EnderecoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import br.com.casa_guido.room.entidades.Endereco as EnderecoEntidade
import br.com.casa_guido.models.Endereco as EnderecoUI

class EnderecoService(
    private val enderecoRepository: EnderecoRepository
) {

    suspend fun enderecos(): Flow<List<EnderecoUI>> {
        val enderecos = enderecoRepository.enderecos
        val listaEnderecos = enderecos.map {
            it.map { endereco ->
                Log.d("EnderecoService", "Endereco: $endereco")
                endereco.toUI()
            }
        }
        return listaEnderecos
    }

    suspend fun getById(id: String): EnderecoUI? {
        return enderecoRepository.getById(id)?.toUI()
    }

    suspend fun createEndereco(endereco: EnderecoUI) {
        Log.d("EnderecoService", "Inserting: $endereco")
        enderecoRepository.insert(endereco.toEntidade())
    }

    private fun EnderecoUI.toEntidade(): EnderecoEntidade {
        Log.d("EnderecoService", this.id + " - " + this.logradouro)
        return EnderecoEntidade(
            id = this.id,
            logradouro = this.logradouro,
            numero = this.numero,
            complemento = this.complemento,
            bairro = this.bairro,
            localidade = this.localidade,
            estado = this.estado,
            cep = this.cep,
            pais = this.pais,
            referencia = this.referencia,
            regiao =  "",
            unidade = "",
            uf = this.uf
        )
    }

    private fun EnderecoEntidade.toUI(): EnderecoUI {
        return EnderecoUI(
            id = this.id,
            logradouro = this.logradouro,
            numero = this.numero,
            bairro = this.bairro,
            estado = this.estado,
            referencia = this.referencia,
            cep = this.cep,
            complemento = this.complemento,
            localidade = this.localidade,
            uf = this.uf,
        )
    }

}
