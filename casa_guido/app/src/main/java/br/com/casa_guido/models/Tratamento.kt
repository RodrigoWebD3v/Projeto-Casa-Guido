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

@Serializable
data class TratamentoDTO(
    val tipo: String = "",
    val data_inicio: String = "",
    val data_ultima_sessao: String = "",
    val observacoes: String = "",
    val outros_tratamentos: String = ""
)

fun Tratamento.toRequestDTO(): TratamentoDTO {
    return TratamentoDTO(
        tipo = this.tipo,
        data_inicio = this.data_inicio,
        data_ultima_sessao = this.data_ultima_sessao,
        observacoes = this.observacoes,
        outros_tratamentos = this.outros_tratamentos
    )
}