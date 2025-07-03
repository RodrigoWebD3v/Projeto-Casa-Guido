package br.com.casa_guido.repository

import android.util.Log
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.dto.DataResponse
import br.com.casa_guido.dto.PacientesRequest
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SincronizarPacientesRepository(
    private val clienteApi: ClienteApi
) {
    suspend fun sincronizarCriarPacientes(dtoPaciente: PacientesRequest): DataResponse {
        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post(clienteApi.pacienteEndpoint) {
                        setBody(dtoPaciente.paciente)
                        headers.append("Authorization", "Bearer ${dtoPaciente.token}")
                    }
                if (response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK) {
                    response.body<DataResponse>()
                } else {
                    throw Exception("Erro ao sincronizar paciente: ${response.bodyAsText()}")
                }

            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun sincronizarAtualizarPacientes(dtoPaciente: PacientesRequest): DataResponse {

        val endpoint = "${clienteApi.pacienteEndpoint}/${dtoPaciente.paciente.idBackend}"

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.put(endpoint) {
                        setBody(dtoPaciente.paciente)
                        headers.append("Authorization", "Bearer ${dtoPaciente.token}")
                    }
                if (response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK) {

                    response.body<DataResponse>()
                } else {
                    throw Exception("Erro ao sincronizar paciente: ${response.bodyAsText()}")
                }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}