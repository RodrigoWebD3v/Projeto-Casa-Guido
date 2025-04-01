package br.com.casa_guido.navigation.NavHost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.casa_guido.navigation.routes.Routes
import br.com.casa_guido.screens.home.Home
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.ButtonText
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {

    var paginaAtual: String by remember {
        mutableStateOf("DashBoard")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Main,
                    titleContentColor = ButtonText,

                ),
                title = {
                    Text(
                        paginaAtual,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 26.sp,
                            color = BackgroundColor,
                        )
                    )
                },
                modifier = Modifier.height(70.dp),
            )
        },

        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.095f),
                containerColor = Main
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        navHostController.navigate(Routes.DashBoard)
                        paginaAtual = "DashBoard"
                    }) {
                        Icon(
                            Icons.Filled.Home, contentDescription = "DashBoard",
                            tint = if (paginaAtual == "DashBoard") ButtonText else Paragraph
                        )
                    }
                    IconButton(onClick = {
                        navHostController.navigate(Routes.Pacientes)
                        paginaAtual = "Pacientes"
                    }) {
                        Icon(
                            Icons.Filled.Face,
                            contentDescription = "Pacientes",
                            tint = if (paginaAtual == "Pacientes") ButtonText else Paragraph
                        )
                    }
                    IconButton(onClick = {
                        navHostController.navigate(Routes.Agenda)
                        paginaAtual = "Agenda"
                    }) {
                        Icon(
                            Icons.Filled.DateRange, contentDescription = "Agenda",
                            tint = if (paginaAtual == "Agenda") ButtonText else Paragraph
                        )
                    }
                    IconButton(onClick = {
                        navHostController.navigate(Routes.Configuracoes)
                        paginaAtual = "Configurações"
                    }) {
                        Icon(
                            Icons.Filled.Settings, contentDescription = "Configurações",
                            tint = if (paginaAtual == "Configurações") ButtonText else Paragraph
                        )
                    }
                }
            }
        },


        ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Routes.DashBoard,
        ) {
            composable<Routes.DashBoard> {
                Home(
                    paddingValues = innerPadding
                )
            }

            composable<Routes.Pacientes> {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    Text("Pacientes")
                }
            }

            composable<Routes.Agenda> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    Text("Agenda")
                }
            }

            composable<Routes.Configuracoes> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    Text("Config")
                }
            }
        }
    }
}

