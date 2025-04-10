package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import br.com.casa_guido.screens.Cirurgia

data class CirurgiaStateUi(
    val toggleDataPickerCirurgia: Boolean = false,
    val listaCirurgias: List<Cirurgia> = emptyList(),
    val cirurgiaEdicao: Cirurgia = Cirurgia()
)
