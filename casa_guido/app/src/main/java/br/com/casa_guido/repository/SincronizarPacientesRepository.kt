package br.com.casa_guido.repository

import android.util.Log
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.dto.ArquivosRequest
import br.com.casa_guido.dto.CreatePacienteResponse
import br.com.casa_guido.dto.DataResponse
import br.com.casa_guido.dto.ListaArquivoResponse
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.dto.UpdatePacienteResponse
import br.com.casa_guido.service.SincronizarPacientesService
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SincronizarPacientesRepository(
    private val clienteApi: ClienteApi
) {
    suspend fun SincronizarCriarPacientesRepository(dtoPaciente: PacientesRequest): DataResponse<CreatePacienteResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post(clienteApi.pacienteEndpoint) {
                        setBody(dtoPaciente.paciente)
                        headers.append("Authorization", "Bearer ${dtoPaciente.token}")
                    }
                if (response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK) {
                    response.body<DataResponse<CreatePacienteResponse>>()
                } else {
                    throw Exception("Erro ao sincronizar paciente: ${response.bodyAsText()}")
                }

            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun SincronizarAtualizarPacientesRepository(dtoPaciente: PacientesRequest): DataResponse<UpdatePacienteResponse> {

        val endpoint = "${clienteApi.pacienteEndpoint}/${dtoPaciente.paciente.idBackend}"

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.put(endpoint) {
                        setBody(dtoPaciente.paciente)
                        headers.append("Authorization", "Bearer ${dtoPaciente.token}")
                    }
                if (response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK) {
                    response.body<DataResponse<UpdatePacienteResponse>>()
                } else {
                    Log.e(
                        "SincronizarPacientesRepository",
                        "Erro ao sincronizar paciente: ${response.bodyAsText()}"
                    )
                    throw Exception("Erro ao sincronizar paciente: ${response.bodyAsText()}")
                }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun EnviarArquivosRequisicaoRepository(
        dtoPaciente: ArquivosRequest,
    ) {
        val endpoint = clienteApi.arquivoEndpoint

        Log.i(
            "EnviarArquivosRequisicao",
            "Enviando arquivos para o paciente: ${dtoPaciente.pacienteIdBackend}, quantidade: ${dtoPaciente.arquivos.size}"
        )

        return try {
            withContext(Dispatchers.IO) {
                val arquivos = dtoPaciente.arquivos

                Log.i("Quantidade de arquivos", "${arquivos.size}")
                arquivos.forEach { arquivo ->
                    Log.i("Enviando arquivo", "Nome: ${arquivo.nome}, URI: ${arquivo.uri}")

                    val bytes = arquivo.conteudoArquivo
                    if (bytes != null) {
                        val base64 =
                            android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)

                        val payload = buildJsonObject {
                            put("fileName", arquivo.nome)
                            put("fileData", base64)
                            put("pacienteId", dtoPaciente.pacienteIdBackend)
                        }

                        val response = clienteApi.client.post(endpoint) {
                            contentType(ContentType.Application.Json)
                            setBody(payload)
                            headers {
                                append(HttpHeaders.Authorization, "Bearer ${dtoPaciente.token}")
                            }
                        }

                        if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                            throw Exception("Erro ao enviar ${arquivo.nome}: ${response.bodyAsText()}")
                        }

                    } else {
                        Log.e(
                            "Arquivo nulo",
                            "Arquivo ${arquivo.nome} está sem conteúdo e não será enviado"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("enviarArquivosRequisicao", "Erro ao enviar arquivos", e)
            throw Exception(e)
        }
    }

    suspend fun buscarPacientes(): List<PacientesRequest> {
        // Implementar lógica para buscar pacientes, se necessário
        return emptyList()
    }

    suspend fun buscarPacientesSemIdBackend(token: String, listaId: SincronizarPacientesService.ListaIds): List<Paciente>? {
        return try {
            val endpoint = "${clienteApi.pacienteEndpoint}/sem-idbackend"
            withContext(Dispatchers.IO) {
                val response = clienteApi.client.post(endpoint) {
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                        append(HttpHeaders.Authorization, token)
                    }

                    setBody(
                        listaId
                    )
                }
                if (response.status == HttpStatusCode.OK) {
                    response.body<DataResponse<List<Paciente>>>().data
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesRepository", "Erro ao buscar pacientes", e)
            null
        }
    }

    suspend fun BuscarArquivosRepository(): DataResponse<ListaArquivoResponse>? {
        return try {
            val endpoint: String = clienteApi.arquivoEndpoint
            withContext(Dispatchers.IO) {
                val response = clienteApi.client.get(endpoint) {
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    }
                }
                if (response.status == HttpStatusCode.OK) {
                    val arquivosResponse = response.body<DataResponse<ListaArquivoResponse>>()
                    arquivosResponse
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("BuscarArquivosRepository", "Erro ao buscar arquivos", e)
            null
        }
    }


}