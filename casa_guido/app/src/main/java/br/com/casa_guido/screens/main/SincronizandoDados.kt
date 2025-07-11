package br.com.casa_guido.screens.main

import br.com.casa_guido.configuration.Status

sealed class SincronizandoDados {
    data class Sincronizando(val mensagem: String = "Sincronizando dados...") : SincronizandoDados()
    data class Sincronizado(val mensagem: String = "Dados Sincronizados!") : SincronizandoDados()
    object Erro : SincronizandoDados()
    object NenhumDado : SincronizandoDados()
}