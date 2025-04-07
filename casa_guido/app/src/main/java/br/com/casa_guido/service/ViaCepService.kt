package br.com.casa_guido.service

import br.com.casa_guido.repository.ViaCepRepository
import br.com.casa_guido.screens.Endereco

class ViaCepService(
    private val viaCepRepository: ViaCepRepository
) {
    suspend fun buscaCep(cep: String): Endereco {
        return viaCepRepository.buscaCep(cep) ?: Endereco()
    }
}