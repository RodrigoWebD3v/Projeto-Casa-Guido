package br.com.casa_guido.screens.ubs.componentes

import br.com.casa_guido.screens.pacientes.componentes.PacienteItem
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.ui.theme.Main

@Composable
fun ListagemUbs(
    modifier: Modifier = Modifier,
    lista: List<Paciente>,
    composable: @Composable () -> Unit,
    onUpId: (String) -> Unit = {}
) {

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.animateScrollToItem(index = 0)
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .border(
                width = 1.dp,
                color = Main,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        item {
            composable()
        }
        items(lista, contentType = { it.id }) { paciente ->
            PacienteItem(
                paciente,
            ) { id ->
                onUpId(id)
            }
        }
    }

}

