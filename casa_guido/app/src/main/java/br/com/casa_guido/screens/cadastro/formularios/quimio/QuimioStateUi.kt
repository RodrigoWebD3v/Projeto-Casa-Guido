package br.com.casa_guido.screens.cadastro.formularios.radio

import br.com.casa_guido.screens.Quimio

data class QuimioStateUi(
    val toggleDataPickerInicioQuimio: Boolean = false,
    val toggleDataPickerFimQuimio: Boolean = false,
    val quimioEdicao: Quimio = Quimio(),
    val onVisibleList: Boolean = false,
)