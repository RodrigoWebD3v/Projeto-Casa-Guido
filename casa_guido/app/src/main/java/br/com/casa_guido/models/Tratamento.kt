package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Tratamento(
    val tipo: String = "",
    val data_inicio: String = "",
    val data_ultima_sessao: String = "",
    val observacoes: String = "",
    val outros_tratamentos: String = "",
    val id : String = UUID.randomUUID().toString()
)