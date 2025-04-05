package br.com.casa_guido.screens.main

import Routes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material3.BottomAppBar
import br.com.casa_guido.ui.theme.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.NavHost.itemNavBar
import br.com.casa_guido.screens.home.HomeScreen
import br.com.casa_guido.screens.pacientes.PacientesScreens
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.launch

@Composable
fun Main(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val items = listOf(
        itemNavBar("Dashboard", Icons.Default.Dashboard, 0),
        itemNavBar("Pacientes", Icons.Default.Groups, 1),
        itemNavBar("Agenda", Icons.Default.CalendarMonth, 2)
    )

    var selectedItem by remember {
        mutableStateOf(
            items.first()
        )
    }

    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val coroutineScope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage

    LaunchedEffect(pagerState.currentPage) {
        selectedItem = items.first { it.id == pagerState.currentPage }
    }

    Scaffold(
        topBar = {
            TopAppBarComp(
                itemNavBar = selectedItem,
                arrowBack = false,
                navHostController = navHostController
            )
        },

        floatingActionButton = {
            when (selectedItem) {
                items[0] -> {

                }

                items[1] -> {
                    FloatingActionButton(
                        onClick = {
                            navHostController.navigate(Routes.CadastroScreenRoute.route)
                        },
                        containerColor = Button
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonAddAlt,
                            contentDescription = "Adicionar",
                            tint = GreenBlack
                        )
                    }
                }

                items[2] -> {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = Button
                    ) {
                        Icon(
                            imageVector = Icons.Default.EditCalendar,
                            contentDescription = "Adicionar",
                            tint = GreenBlack
                        )
                    }
                }
            }
        },

        bottomBar = {
            BottomAppBar(
                containerColor = br.com.casa_guido.ui.theme.Main,
                actions = {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedItem == item,
                            onClick = {
                                selectedItem = item
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(selectedItem.id)
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.texto) },
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
                }
            )
        }
    ) { paddingValues ->
        HorizontalPager(state = pagerState, beyondViewportPageCount = 3) { page ->
            when (page) {
                0 -> {
                    HomeScreen(
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                1 -> {
                    PacientesScreens(
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                2 -> {
                    Box() {
                        Text("Agenda")
                    }
                }
            }
        }
    }

}