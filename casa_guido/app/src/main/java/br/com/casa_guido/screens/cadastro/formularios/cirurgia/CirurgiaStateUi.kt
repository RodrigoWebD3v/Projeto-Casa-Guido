package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import br.com.casa_guido.models.Cirurgia

data class CirurgiaStateUi(
    val toggleDataPickerCirurgia: Boolean = false,
    val cirurgiaEdicao: Cirurgia = Cirurgia(),
    val onVisibleList: Boolean = false,
)
