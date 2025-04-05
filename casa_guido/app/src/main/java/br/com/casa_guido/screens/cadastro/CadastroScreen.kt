package br.com.casa_guido.screens.cadastro

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.navHost.itemNavBar
import br.com.casa_guido.screens.cadastro.components.CamposPaciente.CamposPaciente
import br.com.casa_guido.screens.cadastro.components.CamposPaciente.CamposPacienteUiState
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.screens.shared.DropDownMenu
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userId: String? = null
) {

    val viewModel = koinViewModel<CadastroScreenViewModel>()
    val state by viewModel.uiState.collectAsState()

    var camposPaciente by remember {
        mutableStateOf(
            CamposPacienteUiState()
        )
    }

    if (userId != null) {
        viewModel.getUsuario(id = userId)
    }

    Scaffold(
        topBar = {
            TopAppBarComp(
                itemNavBar = itemNavBar("Cadastro", Icons.Default.PersonAddAlt, 0),
                arrowBack = true,
                navHostController = navHostController
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    println(camposPaciente)
                },
                containerColor = Button
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Adicionar",
                    tint = GreenBlack
                )
            }
        }

    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Main),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            DropDownMenu(
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .padding(start = 20.dp)
                    .padding(vertical = 10.dp),
                opcoes = state.opcoes,
                onSelected = { item ->
                    viewModel.setItemSelecionado(
                        item
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                when (state.itemSelecionado) {
                    state.opcoes[0] -> {
                        CamposPaciente(
                            paciente = state.paciente,
                            onChange = {
                                camposPaciente = it
                            }
                        )
                    }

                    state.opcoes[1] -> {
                        // CamposProntuario()
                    }

                    else -> {
                        CamposPaciente(
                            paciente = state.paciente,
                            onChange = {
                                camposPaciente = it
                            }
                        )
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun CadastroScreenPrev() {
    CadastroScreen(
        userId = null,
        navHostController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current)
    )
}