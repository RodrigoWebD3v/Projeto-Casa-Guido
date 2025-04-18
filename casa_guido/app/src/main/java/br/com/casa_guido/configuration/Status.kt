package br.com.casa_guido.configuration

sealed class Status {
    data class Sucesso(val mensagem: String) : Status()
    data class Desconectado(val mensagem: String) : Status()
    data class Erro(val mensagem: String) : Status()
    object Carregando : Status()
    object SemInteracao : Status()
}
