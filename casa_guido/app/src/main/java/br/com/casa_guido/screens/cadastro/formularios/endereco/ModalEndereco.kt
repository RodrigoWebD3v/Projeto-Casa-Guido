package br.com.casa_guido.screens.cadastro.formularios.endereco

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalEndereco(
    modifier: Modifier = Modifier,
    openBottomSheet: Boolean,
    pessoa: Pessoa,
    onChange: (CamposEndereco, String) -> Unit,
    onDismiss: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

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
                    onDismiss()
                },
                containerColor = BackgroundColor,

                ) {
                Column(
                ) {
                    CadastroEndereco(
                        pessoa = pessoa,
                        onChangeCampo = { campo, valor ->
                            onChange(campo, valor)
                        },
                    )
                }
            }
        }
    }
}