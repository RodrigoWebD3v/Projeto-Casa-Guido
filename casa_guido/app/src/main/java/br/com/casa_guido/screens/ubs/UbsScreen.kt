package br.com.casa_guido.screens.ubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.screens.components.CaixaPesquisa
import br.com.casa_guido.screens.pacientes.ModalBottomSheetComp
import br.com.casa_guido.screens.pacientes.PacientesViewModel
import br.com.casa_guido.screens.pacientes.TipoAcao
import br.com.casa_guido.screens.pacientes.componentes.ListagemPacientes
import br.com.casa_guido.ui.theme.Main
import org.koin.androidx.compose.koinViewModel

@Composable
fun UbsListagem(
    modifier: Modifier = Modifier, onEditeUbs: (String) -> Unit
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<PacientesViewModel>()
    val state by viewModel.uiState.collectAsState()

    var idEdicao by remember { mutableStateOf("") }

    var openBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.carregarPacientes()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Main),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ListagemPacientes(
            composable = {
                CaixaPesquisa(
                    modifier = Modifier,
                    textoParam = state.nome
                ) { valorAtualizado ->
                    viewModel.filtrarPacientes(valorAtualizado)
                }
                Spacer(Modifier.size(10.dp))
            },
            lista = state.listaPacientesFiltrada,
        ) { id ->
            idEdicao = id
            openBottomSheet = true
        }

        ModalBottomSheetComp(
            openBottomSheet = openBottomSheet,
            onClick = {
                when (it) {
                    TipoAcao.EDITAR -> {
                        onEditeUbs(idEdicao)
                        viewModel.filtrarPacientes("")
                        openBottomSheet = false
                    }

                    TipoAcao.COMPARTILHAR -> {
                        viewModel.gerarPdf(idEdicao, context)
                        openBottomSheet = false
                    }

                    TipoAcao.SEM_INTERACAO -> {
                        openBottomSheet = false
                    }
                }
            }
        )
    }
}


@Preview
@Composable
private fun UbsListagemPrev() {
    UbsListagem(
        onEditeUbs = {}
    )
}