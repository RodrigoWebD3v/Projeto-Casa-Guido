package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Arquivo(
    val pacienteId: String = "",
    val nome: String = "",
    val id : String = UUID.randomUUID().toString(),
    val idServidor: String = "",
    val conteudoArquivo: ByteArray = ByteArray(0),
    val uri: String = ""
)
