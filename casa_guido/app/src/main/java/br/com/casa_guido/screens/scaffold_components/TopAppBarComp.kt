package br.com.casa_guido.screens.scaffold_components

import Routes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.navigation.NavHost.itemNavBar
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComp(modifier: Modifier = Modifier, onClickIcon: () -> Unit, itemNavBar: itemNavBar) {

    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundColor,
        ),

        title = {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    itemNavBar.texto, style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        color = Main,
                    )
                )
                Spacer(Modifier.size(5.dp))

                Icon(
                    imageVector = itemNavBar.icon,
                    contentDescription = itemNavBar.texto,
                    tint = Main
                )
            }
        },
        navigationIcon = {
            if (itemNavBar.rota == Routes.CadastroScreenRoute.route) {
                Column(
                    Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        onClickIcon()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Main
                        )
                    }
                }
            }
        },
        modifier = Modifier.height(70.dp),
    )

}