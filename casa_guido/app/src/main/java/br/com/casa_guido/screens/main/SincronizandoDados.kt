package br.com.casa_guido.screens.main

sealed class SincronizandoDados {
    object Sincronizando : SincronizandoDados()
    object Sincronizado : SincronizandoDados()
    object Erro : SincronizandoDados()
    object NenhumDado : SincronizandoDados()
}