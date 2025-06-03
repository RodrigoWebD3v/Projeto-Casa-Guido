package br.com.casa_guido.screens.dadosEscolhaTela

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.DataSaverOff
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import br.com.casa_guido.screens.components.DropDownMenuItem

object OpcoesEscolaridade {
    val escolaridade = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.DataSaverOff, id = "0"),
        DropDownMenuItem(nome = "Fundamental", icone = Icons.Filled.ChildCare, id = "1"),
        DropDownMenuItem(nome = "Médio", icone = Icons.Filled.School, id = "2")
    )

    val serieFundamental = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.ChildCare, id = "0"),
        DropDownMenuItem(nome = "1", icone = Icons.Filled.ChildCare, id = "1"),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.ChildCare, id = "2"),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.ChildCare, id = "3"),
        DropDownMenuItem(nome = "4", icone = Icons.Filled.ChildCare, id = "4"),
        DropDownMenuItem(nome = "5", icone = Icons.Filled.ChildCare, id = "5"),
        DropDownMenuItem(nome = "6", icone = Icons.Filled.ChildCare, id = "6"),
        DropDownMenuItem(nome = "7", icone = Icons.Filled.ChildCare, id = "7"),
        DropDownMenuItem(nome = "8", icone = Icons.Filled.ChildCare, id = "8"),
        DropDownMenuItem(nome = "9", icone = Icons.Filled.ChildCare, id = "9"),
    )

    val serieMedio = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.School, id = "0"),
        DropDownMenuItem(nome = "1", icone = Icons.Filled.School, id = "1"),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.School, id = "2"),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.School, id = "3"),
    )

    val opcoesDefault = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.DataSaverOff, id = "0")
    )
}