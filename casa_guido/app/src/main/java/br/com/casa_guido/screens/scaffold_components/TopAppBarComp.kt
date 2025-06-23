package br.com.casa_guido.screens.scaffold_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.screens.cadastro.ItemNavBar
import br.com.casa_guido.ui.theme.Alert

import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComp(
    modifier: Modifier = Modifier,
    arrowBack: Boolean,
    itemNavBar: ItemNavBar,
    navHostController: NavHostController
) {
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()

    val context = LocalContext.current

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

                Spacer(Modifier.weight(1f))

                Box(
                    Modifier
                        .background(Alert,RoundedCornerShape(8.dp)).clickable {
                            viewModelAuthMananger.logout(
                                context = context
                            )
                        },

                ) {
                    Text("Sair", modifier = Modifier.padding(horizontal = 10.dp), style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Main
                    ))
                }
            }
        },
        navigationIcon = {
            if (arrowBack) {
                Column(
                    Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        navHostController.popBackStack()
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