package br.com.casa_guido.configuration.exception

sealed class AppException(message: String) : Exception(message) {
    class SemConexao : AppException("Sem conexão com a internet.")
    class SessaoExpirada : AppException("Sessão expirada. Faça login novamente.")
    class Timeout : AppException("A requisição demorou demais. Tente novamente.")
    class ErroDesconhecido : AppException("Ocorreu um erro inesperado.")

}
