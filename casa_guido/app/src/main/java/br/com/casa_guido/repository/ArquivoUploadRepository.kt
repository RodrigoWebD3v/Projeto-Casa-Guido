package br.com.casa_guido.repository

import br.com.casa_guido.configuration.ClienteApi
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.content.MultiPartFormDataContent
import io.ktor.http.content.PartData
import io.ktor.http.content.formData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ArquivoUploadRepository(
    private val clienteApi: ClienteApi
) {
    suspend fun uploadArquivo(arquivo: File, pacienteId: String): String {
        return try {
            withContext(Dispatchers.IO) {
                val response = clienteApi.client.post("${'$'}{clienteApi.pacienteEndpoint}/arquivos") {
                    setBody(
                        MultiPartFormDataContent(
                            formData {
                                append("pacienteId", pacienteId)
                                append(
                                    "arquivo",
                                    arquivo.readBytes(),
                                    Headers.build {
                                        append(HttpHeaders.ContentDisposition, "filename=\"${'$'}{arquivo.name}\"")
                                    }
                                )
                            }
                        )
                    )
                }
                response.body<String>()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}
