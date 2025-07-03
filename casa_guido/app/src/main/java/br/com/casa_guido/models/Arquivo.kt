package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Arquivo(
    val pacienteId: String,
    val nome: String? = null,
    val id : String = UUID.randomUUID().toString(),
    val idServidor: String? = null,
    val conteudoArquivo: ByteArray? = null,
    val uri: String? = null
)
