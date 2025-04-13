package br.com.casa_guido.configuration

sealed class Resultado {
    data class Sucesso(val mensagem: String) : Resultado()
    data class Desconectado(val mensagem: String) : Resultado()
    data class Erro(val mensagem: String) : Resultado()
    object Carregando : Resultado()
    object SemInteracao : Resultado()
}
