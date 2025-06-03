package br.com.casa_guido.screens.components.escolaridade

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import br.com.casa_guido.screens.components.DropDownMenu
import br.com.casa_guido.screens.dadosEscolhaTela.OpcoesEscolaridade
import br.com.casa_guido.ui.theme.GreenBlack
import org.koin.androidx.compose.koinViewModel

@Composable
fun Escolaridade(
    modifier: Modifier = Modifier, pessoa: Pessoa, onChangeEscolaridade: (Int, Int) -> Unit
) {
    val viewModel = koinViewModel<EscolaridadeViewModel>()

    var educacaoState = viewModel.escolaridadeUiState.collectAsState()

    LaunchedEffect(Unit) {
        Log.i(
            "Escolaridade",
            "Inicializando com escolaridade: ${pessoa.escolaridade} e série: ${pessoa.serie}"
        )
        viewModel.updateEscolaridadePorId(pessoa.escolaridade, pessoa.serie)
    }

    Column(
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth(.9f)
        ) {
            Text(
                "Escolaridade", style = TextStyle(
                    fontSize = 14.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.size(5.dp))
        DropDownMenu(
            opcoes = educacaoState.value.escolaridadeOptions,
            valorPreenchido = educacaoState.value.escolaridade,
        ) {
            viewModel.updateEscolaridade(it, educacaoState.value.serie)
            onChangeEscolaridade(
                it.second,
                educacaoState.value.serie.second
            )
        }

        Spacer(Modifier.size(10.dp))

        Row(
            Modifier.fillMaxWidth(.9f)
        ) {
            Text(
                "Série", style = TextStyle(
                    fontSize = 14.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.size(5.dp))

        DropDownMenu(
            valorPreenchido =  educacaoState.value.serie,
            opcoes = when (educacaoState.value.escolaridade.second) {
                1 -> educacaoState.value.serieFundamentalOptions
                2 -> educacaoState.value.serieMedioOptions
                else -> educacaoState.value.default
            }
        ) {
            Log.i("Escolaridade", "Série selecionada: ${it.first} - ID: ${it.second}")
            viewModel.updateEscolaridade(educacaoState.value.escolaridade, it)
            onChangeEscolaridade(
                educacaoState.value.escolaridade.second,
                it.second
            )
        }
    }


}