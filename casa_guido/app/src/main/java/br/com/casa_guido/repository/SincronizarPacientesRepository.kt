package br.com.casa_guido.repository

import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.dto.PacientesResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SincronizarPacientesRepository(
    private val clienteApi: ClienteApi
) {
    suspend fun sincronizarPacientes(dtoPaciente: PacientesRequest): PacientesResponse {

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post(clienteApi.pacienteEndpoint) {
                        setBody(dtoPaciente)
                    }
                response.body<PacientesResponse>()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}