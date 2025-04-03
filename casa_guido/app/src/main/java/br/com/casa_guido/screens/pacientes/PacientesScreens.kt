package br.com.casa_guido.screens.pacientes

import Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.navigation.NavHost.itemNavBar
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.listaPacientes
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.screens.shared.CaixaPesquisa
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun PacientesScreens(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
    selectedItemParam: itemNavBar
) {

    val viewMode = koinViewModel<PacientesViewModel>()
    val state = viewMode.uiState.collectAsState()

    var listaPacientesState by remember { mutableStateOf(state.value.pacientes) }

    LaunchedEffect(state.value.nome) {
        listaPacientesState = filterPacientes(
            listaPacientesState,
            state.value.nome
        )
    }

    val items = remember {
        listOf<itemNavBar>(
            itemNavBar(
                texto = "Dashboard",
                icon = Icons.Default.Dashboard,
                rota = Routes.DashBoardScreenRoute.route
            ),
            itemNavBar(
                texto = "Pacientes",
                icon = Icons.Default.Groups,
                rota = Routes.PacientesScreenRoute.route
            ),
            itemNavBar(
                texto = "Agenda",
                icon = Icons.Default.CalendarMonth,
                rota = Routes.AgendaScreenRoute.route
            )
        )
    }

    val selectedItem = remember { mutableStateOf(selectedItemParam) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                onClickIcon = { onNavigate(Routes.PacientesScreenRoute.route) },
                itemNavBar = selectedItem.value
            )
        },

        bottomBar = {
            BottomAppBar(
                actions = {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedItem.value == item,
                            onClick = {
                                if (
                                    selectedItem.value != item
                                ) {
                                    selectedItem.value = item
                                    onNavigate(item.rota)
                                }
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = item.texto,

                                    )
                            },
                            label = { Text(item.texto) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenBlack,
                                unselectedIconColor = Paragraph,
                                selectedTextColor = GreenBlack,
                                unselectedTextColor = Paragraph,
                                indicatorColor = Color.LightGray
                            )
                        )
                    }
                },
                containerColor = Main,
            )
        },

        ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                    textoParam = state.value.nome
                ) { valorAtualizado ->
                    state.value.buscaPaciente(valorAtualizado)
                }

                Button(
                    onClick = {
                        onNavigate(
                            Routes.CadastroScreenRoute.route
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Button,
                        contentColor = GreenBlack
                    ),
                    modifier = Modifier.size(40.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PersonAddAlt,
                        contentDescription = "Adicionar Paciente"
                    )
                }

            }


            LazyColumn {
                items(listaPacientesState.size) { index ->
                    PacienteItem(
                        paciente = listaPacientesState[index],
                    )
                }
            }
        }
    }
}

fun filterPacientes(
    pacientes: List<Paciente>,
    query: String
): List<Paciente> {
    return pacientes.filter { paciente ->
        paciente.nome.contains(query, ignoreCase = true)
    }
}

@Preview
@Composable
private fun PacientesPrev() {
    PacientesScreens(
        onNavigate = {},
        selectedItemParam = itemNavBar(
            texto = "Pacientes",
            icon = Icons.Default.Groups,
            rota = Routes.PacientesScreenRoute.route
        )
    )
}