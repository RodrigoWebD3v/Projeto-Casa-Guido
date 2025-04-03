package br.com.casa_guido.screens.cadastro

import Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.casa_guido.navigation.NavHost.itemNavBar
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.screens.shared.DropDownMenu
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Routes) -> Unit
) {
    val tela = itemNavBar(
        texto = "Cadastro de Paciente",
        icon = Icons.Default.PersonAddAlt,
        rota = Routes.CadastroScreenRoute.route
    )


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                onClickIcon = { onNavigate(Routes.PacientesScreenRoute) },
                itemNavBar = tela
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Button,
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Salvar",
                    tint = GreenBlack
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Main),
            verticalArrangement = Arrangement.Top
        ) {
            DropDownMenu()
        }
    }
}
