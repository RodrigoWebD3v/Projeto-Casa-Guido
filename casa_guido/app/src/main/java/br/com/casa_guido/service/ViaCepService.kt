package br.com.casa_guido.service

import br.com.casa_guido.repository.ViaCepRepository
import br.com.casa_guido.models.Endereco

class ViaCepService(
    private val viaCepRepository: ViaCepRepository
) {
    suspend fun buscaCep(cep: String): Endereco? {
        try {
            return viaCepRepository.buscaCep(cep)
        } catch (e: Exception) {
            throw e
        }
    }
}