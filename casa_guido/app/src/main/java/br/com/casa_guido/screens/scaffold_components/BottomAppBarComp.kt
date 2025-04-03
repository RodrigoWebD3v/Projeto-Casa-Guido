package br.com.casa_guido.screens.scaffold_components

import Routes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.ui.theme.Secondary

@Composable
fun BottomAppBarComp(
    modifier: Modifier = Modifier,
    paginaAtual: Routes,
    navHostController: NavHostController
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
            .border(
                width = 1.dp,
                color = Secondary,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
            ),

        containerColor = Main
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {
                navHostController.navigate(Routes.DashBoardScreenRoute)
            }) {
                Icon(
                    Icons.Filled.Dashboard,
                    contentDescription = "DashBoard",
                    tint = if (paginaAtual == Routes.DashBoardScreenRoute) GreenBlack else Paragraph
                )
            }
            IconButton(onClick = {
                navHostController.navigate(Routes.PacientesScreenRoute)
            }) {
                Icon(
                    Icons.Filled.Groups,
                    contentDescription = "Pacientes",
                    tint = if (paginaAtual == Routes.PacientesScreenRoute) GreenBlack else Paragraph
                )
            }
            IconButton(onClick = {
                navHostController.navigate(Routes.AgendaScreenRoute)
            }) {
                Icon(
                    Icons.Filled.CalendarMonth,
                    contentDescription = "Agenda",
                    tint = if (paginaAtual == Routes.AgendaScreenRoute) GreenBlack else Paragraph
                )
            }
            IconButton(onClick = {
                navHostController.navigate(Routes.ConfiguracoesScreenRoute)
            }) {
                Icon(
                    Icons.Filled.Settings, contentDescription = "Configurações",
                    tint = if (paginaAtual == Routes.ConfiguracoesScreenRoute) GreenBlack else Paragraph
                )
            }
        }
    }

}