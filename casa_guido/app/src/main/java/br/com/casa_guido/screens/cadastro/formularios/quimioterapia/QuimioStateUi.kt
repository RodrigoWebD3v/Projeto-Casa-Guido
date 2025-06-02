package br.com.casa_guido.screens.cadastro.formularios.radioterapia

import br.com.casa_guido.models.Quimio

data class QuimioStateUi(
    val toggleDataPickerInicioQuimio: Boolean = false,
    val toggleDataPickerFimQuimio: Boolean = false,
    val quimioEdicao: Quimio = Quimio(),
    val onVisibleList: Boolean = false,
)