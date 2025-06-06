package br.com.casa_guido.screens.pacientes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetComp(
    modifier: Modifier = Modifier,
    openBottomSheet: Boolean,
    onClick: (TipoAcao) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(sheetState) {
        if (openBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Main
    ) {
        if (openBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    onClick(TipoAcao.SEM_INTERACAO)
                },
                containerColor = BackgroundColor,
            ) {
                Column {
                    Box(
                        modifier = Modifier.clickable {
                            onClick(
                                TipoAcao.EDITAR
                            )
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Editar Paciente", color = Main)
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.clickable {
                            onClick(
                                TipoAcao.COMPARTILHAR
                            )
                        }
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Gerar PDF", color = Main)
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }

}