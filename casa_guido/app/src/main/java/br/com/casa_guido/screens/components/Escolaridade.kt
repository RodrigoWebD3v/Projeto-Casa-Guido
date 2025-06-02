package br.com.casa_guido.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.ui.theme.GreenBlack

@Composable
fun Escolaridade(
    modifier: Modifier = Modifier,
    pessoa: Pessoa,
    onChangeEscolaridade: (String, String) -> Unit
) {

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
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "1"),
        DropDownMenuItem(nome = "Fundamental", icone = Icons.Filled.Home, id = "2"),
        DropDownMenuItem(nome = "Médio", icone = Icons.Filled.Settings, id = "3")
    )

    val serieFundamental = listOf(
        DropDownMenuItem(nome = "1", icone = Icons.Filled.Check, id = "1"),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.Check, id = "2"),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.Check, id = "3"),
        DropDownMenuItem(nome = "4", icone = Icons.Filled.Check, id = "4"),
        DropDownMenuItem(nome = "5", icone = Icons.Filled.Check, id = "5"),
        DropDownMenuItem(nome = "6", icone = Icons.Filled.Check, id = "6"),
        DropDownMenuItem(nome = "7", icone = Icons.Filled.Check, id = "7"),
        DropDownMenuItem(nome = "8", icone = Icons.Filled.Check, id = "8"),
        DropDownMenuItem(nome = "9", icone = Icons.Filled.Check, id = "9"),
    )

    val serieMedio = listOf(
        DropDownMenuItem(nome = "1", icone = Icons.Filled.Home, id = "1"),
        DropDownMenuItem(nome = "2", icone = Icons.Filled.Settings, id = "2"),
        DropDownMenuItem(nome = "3", icone = Icons.Filled.Settings, id = "3"),
    )

    val opcoesDefault = listOf(
        DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "1")
    )

    Column(
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth(.9f)
        ) {
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

        Spacer(Modifier.size(5.dp))
        DropDownMenu(
            opcoes = escolaridade,
            valorPreenchido = escolaridade[pessoa.escolaridade],
        ) {
            escolaridadeState = it
        }

        Spacer(Modifier.size(10.dp))

        Row(
            Modifier.fillMaxWidth(.9f)
        ) {
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
        Spacer(Modifier.size(5.dp))
        DropDownMenu(
            valorPreenchido = when (escolaridadeState.id) {
                "1" -> serieFundamental[pessoa.serie]
                "2" -> serieMedio[pessoa.serie]
                else -> opcoesDefault[0]
            },
            opcoes = when (escolaridadeState.id) {
                "1" -> serieFundamental
                "2" -> serieMedio
                else -> opcoesDefault
            }
        ) {
            seriestate = it.id
        }
    }


}