package br.com.casa_guido.screens.home

import Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.navigation.NavHost.itemNavBar
import br.com.casa_guido.screens.home.grid_home.GridItemData
import br.com.casa_guido.screens.home.grid_home.GridScreen
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAgenda: () -> Unit,
    onPacientes: () -> Unit,
    selectedItemParam: itemNavBar
) {

    val viewModel = koinViewModel<HomeViewModel>()

    val state = viewModel.uiState.collectAsState()

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
                onClickIcon = {  },
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
                                    onPacientes()
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
                .padding(
                    innerPadding
                )
                .background(
                    color = Main
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            GridScreen(
                gridItemsList = listOf(
                    GridItemData(
                        state.value.totalPacientes.title,
                        state.value.totalPacientes.valorPrincipal,
                        state.value.totalPacientes.subtitulo
                    ),
                    GridItemData(
                        state.value.pacientesHoje.title,
                        state.value.pacientesHoje.valorPrincipal,
                        state.value.pacientesHoje.subtitulo
                    ),
                    GridItemData(
                        state.value.pacientesSemana.title,
                        state.value.pacientesSemana.valorPrincipal,
                        state.value.pacientesSemana.subtitulo
                    ),
                    GridItemData(
                        state.value.comparecimento.title,
                        state.value.comparecimento.valorPrincipal,
                        state.value.comparecimento.subtitulo
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .border(
                        width = 1.dp,
                        color = Main,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                    )
            ) {

                state.value.agendamentos.forEach { agendamento ->
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Paragraph,
                        thickness = 1.dp
                    )
                    ItemAgendamento(agendamento = agendamento)
                }
            }
        }
    }


}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(

        onAgenda = {},
        onPacientes = {},
        selectedItemParam = itemNavBar(
            texto = "Dashboard",
            icon = Icons.Default.Dashboard,
            rota = Routes.DashBoardScreenRoute.route
        )
    )
}



