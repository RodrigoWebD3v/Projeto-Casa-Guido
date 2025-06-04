package br.com.casa_guido.screens.components.escolaridade

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.screens.components.DropDownMenu
import br.com.casa_guido.ui.theme.GreenBlack
import org.koin.androidx.compose.koinViewModel

@Composable
fun Escolaridade(
    modifier: Modifier = Modifier,
    pessoa: Pessoa,
    onChangeEscolaridade: (Int, Int) -> Unit
) {
    val viewModel = koinViewModel<EscolaridadeViewModel>()
    val educacaoState = viewModel.escolaridadeUiState.collectAsState()

    val estado = educacaoState.value

    Column(modifier = modifier) {
        Label("Escolaridade")

        DropDownMenu(
            opcoes = estado.escolaridadeOptions,
            valorPreenchido = estado.escolaridadeOptions.firstOrNull { it.second == pessoa.escolaridade }
                ?: estado.escolaridadeOptions.first(),
        ) { selected ->
            onChangeEscolaridade(
                selected.second,
                0
            )
        }

        Spacer(Modifier.size(10.dp))

        Label("Série")

        val serieOptions = when (pessoa.escolaridade) {
            1 -> estado.serieFundamentalOptions
            2 -> estado.serieMedioOptions
            else -> estado.default
        }

        DropDownMenu(
            opcoes = serieOptions,
            valorPreenchido = serieOptions.firstOrNull { it.second == pessoa.serie }
                ?: serieOptions.first(),
        ) { selected ->
            onChangeEscolaridade(
                pessoa.escolaridade,
                selected.second
            )
        }
    }
}

@Composable
private fun Label(text: String) {
    Row(Modifier.fillMaxWidth(0.9f)) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    Spacer(Modifier.size(5.dp))
}
