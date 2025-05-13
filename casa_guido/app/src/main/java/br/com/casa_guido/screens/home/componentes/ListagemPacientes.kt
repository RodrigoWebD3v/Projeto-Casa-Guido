package br.com.casa_guido.screens.home.componentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.casa_guido.screens.Agendamento
import br.com.casa_guido.ui.theme.Main

@Composable
fun ListagemAgendamentos(
    modifier: Modifier = Modifier,
    lista: List<Agendamento>,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.animateScrollToItem(index = 0) // Exemplo
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .border(
                width = 1.dp,
                color = Main,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )
    ) {
        items(lista, contentType = {it.id}) { agendamento ->
            ItemAgendamento(
                agendamento
            )
        }
    }

}