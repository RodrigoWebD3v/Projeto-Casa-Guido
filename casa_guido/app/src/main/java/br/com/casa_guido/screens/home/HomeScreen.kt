package br.com.casa_guido.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.home.componentes.ListagemAgendamentos
import br.com.casa_guido.screens.home.componentes.grid_home.GridItemData
import br.com.casa_guido.screens.home.componentes.grid_home.GridScreen
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val viewModel = koinViewModel<HomeViewModel>()

    val state by viewModel.uiState.collectAsState()

    Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = Main
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            GridScreen(
                gridItemsList = listOf(
                    GridItemData(
                        state.totalPacientes.title,
                        state.totalPacientes.valorPrincipal,
                        state.totalPacientes.subtitulo
                    ),
                    GridItemData(
                        state.pacientesHoje.title,
                        state.pacientesHoje.valorPrincipal,
                        state.pacientesHoje.subtitulo
                    ),
                    GridItemData(
                        state.pacientesSemana.title,
                        state.pacientesSemana.valorPrincipal,
                        state.pacientesSemana.subtitulo
                    ),
                    GridItemData(
                        state.comparecimento.title,
                        state.comparecimento.valorPrincipal,
                        state.comparecimento.subtitulo
                    ),
                )
            )

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Proximos Agendamentos",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = GreenBlack,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    ),
                )
                Text(
                    "Agendamentos para hoje",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = GreenBlack,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    ),
                )
            }

        ListagemAgendamentos(
            lista = state.agendamentos
        )

    }


}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(

    )
}



