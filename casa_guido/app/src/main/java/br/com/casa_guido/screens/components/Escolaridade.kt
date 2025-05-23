package br.com.casa_guido.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.GreenBlack

@Composable
fun Escolaridade(modifier: Modifier = Modifier, onChangeEscolaridade : (String, String) -> Unit) {

    var escolaridadeState by remember {
        mutableStateOf(
            DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0")
        )
    }

    var seriestate by remember {
        mutableStateOf(
            "0"
        )
    }

    val escolaridade = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0"),
        DropDownMenuItem(nome = "Fundamental", icone = Icons.Filled.Home, id = "1"),
        DropDownMenuItem(nome = "Médio", icone = Icons.Filled.Settings, id = "2")
    )

    val serieFundamental = listOf(
        DropDownMenuItem(nome = "1", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "4", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "5", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "6", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "7", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "8", icone = Icons.Filled.Check),
        DropDownMenuItem(nome = "9", icone = Icons.Filled.Check),
    )

    val serieMedio = listOf(
        DropDownMenuItem(nome = "1", icone = Icons.Filled.Home),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.Settings),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.Settings),
    )

    val opcoesDefault = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0")
    )

    Row (
        Modifier.fillMaxWidth(.9f)
    ){
        Text(
            "Escolaridade",
            style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            ), maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }


    DropDownMenu(
        opcoes = escolaridade,
        modifier = Modifier.fillMaxWidth(.9f)
    ) {
        escolaridadeState = it
    }


    Row (
        Modifier.fillMaxWidth(.9f)
    ){
        Text(
            "Série",
            style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            ), maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }

    DropDownMenu(
        modifier = Modifier.fillMaxWidth(.9f),
        opcoes = when (escolaridadeState.id) {
            "1" -> serieFundamental
            "2" -> serieMedio
            else -> opcoesDefault
        }
    ) {
        seriestate = it.id
    }
}