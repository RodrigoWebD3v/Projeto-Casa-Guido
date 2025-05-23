package br.com.casa_guido.screens.main

import Routes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.screens.cadastro.ItemNavBar
import br.com.casa_guido.screens.home.HomeScreen
import br.com.casa_guido.screens.pacientes.PacientesScreens
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun Main(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    navHostController: NavHostController,
    mensagemSucesso: Status
) {

    val authViewModel = koinViewModel<ViewModelAuthMananger>()
    val statusUsuario by authViewModel.statusUsuario.collectAsState()
    val viewModelMain = koinViewModel<MainViewModel>()
    val primeiroAcesso by viewModelMain.primeiroAcesso.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val contexto = LocalContext.current

    val items = listOf(
        ItemNavBar("Dashboard", Icons.Default.Dashboard, 0),
        ItemNavBar("Pacientes", Icons.Default.Groups, 1),
        ItemNavBar("Ubs", Icons.Default.LocalHospital, 2)
    )

    var selectedItem by remember {
        mutableStateOf(
            items.first()
        )
    }

    var dadosParaSincronizar by remember {
        mutableStateOf(
            false
        )
    }

    val infiniteTransition = rememberInfiniteTransition(label = "sync")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )

    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val coroutineScope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage

    LaunchedEffect(pagerState.currentPage) {
        selectedItem = items.first { it.id == pagerState.currentPage }
    }

    LaunchedEffect(statusUsuario) {
        when (statusUsuario) {
            true -> {

            }
            false -> {
                onNavigateToLogin()
            }
        }
    }

    LaunchedEffect(mensagemSucesso, primeiroAcesso) {
        if (!primeiroAcesso) {
            val mensagem = (mensagemSucesso as? Status.Sucesso)?.mensagem
            mensagem?.let {
                snackbarHostState.showSnackbar(
                    message = it,
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
            }
            viewModelMain.togglePrimeiroAcesso()
        }

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        actionColor = GreenBlack,
                        contentColor = GreenBlack,
                        backgroundColor = Paragraph
                    )
                }
            )
        },

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
                    if (dadosParaSincronizar) {
                        FloatingActionButton(
                            onClick = {},
                            containerColor = Button,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sync,
                                contentDescription = "Sincronizar",
                                tint = GreenBlack,
                                modifier = Modifier.graphicsLayer {
                                    rotationZ = rotation
                                }
                            )
                        }
                    }
                }

                items[1] -> {
                    FloatingActionButton(
                        onClick = {
                            navHostController.navigate(Routes.CadastroScreenRoute.parametroOpicional())
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
                        onClick = {
                            authViewModel.logout(contexto)
                        },
                        containerColor = Button
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalHospital,
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
                    ) { id ->
                        navHostController.navigate(Routes.CadastroScreenRoute.parametroOpicional(id))
                    }
                }

                2 -> {
                    Box(Modifier.clickable {
                        Sessao.clearSession(contexto)
                    }) {
                        Text("Agenda")
                    }
                }
            }
        }
    }
}