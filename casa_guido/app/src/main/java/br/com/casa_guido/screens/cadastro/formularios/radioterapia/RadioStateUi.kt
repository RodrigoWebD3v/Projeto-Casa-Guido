package br.com.casa_guido.screens.cadastro.formularios.radioterapia

import br.com.casa_guido.models.Radio

data class RadioStateUi(
    val toggleDataPickerInicioRadio: Boolean = false,
    val toggleDataPickerFimRadio: Boolean = false,
    val radioEdicao: Radio = Radio(),
    val onVisibleList: Boolean = false,
)