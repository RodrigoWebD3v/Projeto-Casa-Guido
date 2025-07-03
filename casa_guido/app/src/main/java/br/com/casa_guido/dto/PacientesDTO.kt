package br.com.casa_guido.dto

import br.com.casa_guido.models.PacienteRequestDTO
import kotlinx.serialization.Serializable


@Serializable
data class PacientesRequest(
    val paciente: PacienteRequestDTO,
    val token: String
)

@Serializable
data class ArquivoRequest(
    val nome: String? = null,
    val uri: String? = null,
    val conteudoArquivo: ByteArray? = null,
)

@Serializable
data class ArquivosRequest(
    val pacienteIdBackend: String,
    val arquivos: List<ArquivoRequest>,
    val token: String
)

@Serializable
data class CreatePacienteResponse(
    val id: String,
    val nome: String
)

@Serializable
data class UpdatePacienteResponse(
    val id: String,
)

@Serializable
data class DataResponse<T>(
    val data: T,
)

@Serializable
data class ArquivoResponse(
    val message: String
)

@Serializable
data class ArquivoResponseGet(
    val nome: String,
    val base64: String,
)

@Serializable
data class ListaArquivoResponse(
    val idPaciente: String,
    val arquivos: List<ArquivoResponseGet>
)