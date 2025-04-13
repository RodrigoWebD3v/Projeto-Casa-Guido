package br.com.casa_guido.configuration

sealed class Conexao {
    object Conectado : Conexao()
    object SemConexao : Conexao()
    object SemInformacao : Conexao()
}
