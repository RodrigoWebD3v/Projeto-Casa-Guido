package br.com.casa_guido.screens.pacientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.screens.pacientes.componentes.ListagemPacientes
import br.com.casa_guido.screens.shared.CaixaPesquisa
import br.com.casa_guido.ui.theme.Main
import org.koin.androidx.compose.koinViewModel

@Composable
fun PacientesScreens(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<PacientesViewModel>()
    val state by viewModel.uiState.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.animateScrollToItem(index = 0) // Exemplo
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Main),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CaixaPesquisa(
                modifier = Modifier.weight(1f),
                textoParam = state.nome
            ) { valorAtualizado ->
                viewModel.filtrarPacientes(valorAtualizado)
            }
        }

        ListagemPacientes(
            lista = state.listaPacientesFiltrada,
        )
    }
}

@Preview
@Composable
private fun PacientesPrev() {
    PacientesScreens(

    )
}