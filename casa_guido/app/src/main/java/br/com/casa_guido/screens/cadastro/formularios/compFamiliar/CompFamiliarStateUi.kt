package br.com.casa_guido.screens.cadastro.formularios.compFamiliar

import br.com.casa_guido.screens.ComposicaoFamiliar

data class CompFamiliarStateUi(
    val toggleDataPickerNascimento: Boolean = false,
    val composicaoFamiliarEdicao: ComposicaoFamiliar = ComposicaoFamiliar(),
    val onVisibleList: Boolean = false,
)
