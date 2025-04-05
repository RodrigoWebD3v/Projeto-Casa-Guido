package br.com.casa_guido.screens.cadastro

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.PersonAddAlt
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.shared.DropDownMenuItem

data class CadastroScreenUiState(
    val opcoes: List<DropDownMenuItem> = listOf(
        DropDownMenuItem(nome = "Paciente", icone = Icons.Default.PersonAddAlt),
        DropDownMenuItem(nome = "Prontuário", icone = Icons.Default.MedicalInformation)
    ),

    val itemSelecionado: DropDownMenuItem? = null,

    val paciente: Paciente = Paciente(),
)