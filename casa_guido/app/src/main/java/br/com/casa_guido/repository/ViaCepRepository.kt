package br.com.casa_guido.repository

import br.com.casa_guido.configuration.ApiViaCepConfig
import br.com.casa_guido.screens.Endereco
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ViaCepRepository(
    private val apiViaCepConfig: ApiViaCepConfig
) {

    suspend fun buscaCep(cep: String): Endereco? {
        return try {
            withContext(Dispatchers.IO) {
                val response =
                    apiViaCepConfig.client.get("${apiViaCepConfig._endpointViaCep}$cep/json/")
                response.body<Endereco>()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}