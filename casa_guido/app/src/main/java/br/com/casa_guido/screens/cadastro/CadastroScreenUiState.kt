package br.com.casa_guido.screens.cadastro

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.PersonAddAlt
import br.com.casa_guido.models.Cirurgia
import br.com.casa_guido.screens.components.DropDownMenuItem

data class CadastroScreenUiState(
    val opcoes: List<DropDownMenuItem> = listOf(
        DropDownMenuItem(nome = "Paciente", icone = Icons.Default.PersonAddAlt),
        DropDownMenuItem(nome = "Prontuário", icone = Icons.Default.MedicalInformation)
    ),
    val itemSelecionado: Int = 0,

    val dataPickerNascimentoShow : Boolean = false,

    val dataPickerCirurgiaShow : Boolean = false,

    val cirurgia: Cirurgia = Cirurgia(),

    val edicao : Boolean = false,
)