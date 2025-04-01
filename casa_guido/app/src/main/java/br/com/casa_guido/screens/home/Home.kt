package br.com.casa_guido.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.ButtonText
import br.com.casa_guido.ui.theme.Highlight
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph


val listaAgendamentos = listOf(
    Agendamento(nome = "Ana Silva", horario = "08:00", tipo = "Tipo 1", status = "Confirmado"),
    Agendamento(nome = "Carlos Oliveira", horario = "09:00", tipo = "Tipo 2", status = "Pendente"),
    Agendamento(nome = "Mariana Santos", horario = "10:00", tipo = "Tipo 3", status = "Confirmado"),
    Agendamento(nome = "Pedro Costa", horario = "10:00", tipo = "Tipo 3", status = "Confirmado"),
    Agendamento(nome = "Juliana Lima", horario = "10:00", tipo = "Tipo 3", status = "Pendente")
)


fun getInitials(name: String): String {
    return name.split(" ").filter { it.isNotEmpty() }.map { it.first().uppercaseChar() }
        .joinToString("")
}

@Composable
fun Home(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(
                color = BackgroundColor
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GridScreen()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Main),
            contentAlignment = Alignment.Center
        ) {
            Text("LINE CHARTS")
        }


        LazyColumn {
            items(listaAgendamentos) { agendamento ->
                ItemAgendamento(agendamento = agendamento)
            }
        }
    }
}

@Composable
fun ItemAgendamento(agendamento: Agendamento) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Main),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        Paragraph
                    ), contentAlignment = Alignment.Center
            ) {
                Text(
                    getInitials(agendamento.nome),
                    style = TextStyle(),
                    fontSize = 16.sp,
                    color = BackgroundColor,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.size(10.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    agendamento.nome,
                    style = TextStyle(),
                    fontSize = 16.sp,
                    color = BackgroundColor,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    agendamento.horario,
                    style = TextStyle(),
                    fontSize = 12.sp,
                    color = BackgroundColor,
                    fontWeight = FontWeight.Medium
                )
            }


        }
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .width(100.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if(agendamento.status == "Confirmado") BackgroundColor else Highlight),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                agendamento.status,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = if(agendamento.status == "Confirmado") Color.White else ButtonText,
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home(
        modifier = Modifier,
        paddingValues = PaddingValues(0.dp)
    )
}


data class GridItemData(
    val title: String, val valorPrincipal: String, val subtitulo: String
)


@Composable
fun GridScreen() {
    val gridItems = listOf(
        GridItemData("Total de Pacientes", "100", "+12% este mes"),
        GridItemData("Hoje", "12", "3 pentendes"),
        GridItemData("Semana", "42", "+8% vs anterior"),
        GridItemData("Comparecimento", "92%", "+2% este mes")
    )

    val items = gridItems

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Define um Grid de 2 colunas
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            GridItem(item)
        }
    }
}

@Composable
fun GridItem(dado: GridItemData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                dado.title, style = TextStyle(
                    fontSize = 12.sp, color = BackgroundColor, fontWeight = FontWeight.Medium
                ), modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                dado.valorPrincipal, style = TextStyle(
                    fontSize = 22.sp, color = BackgroundColor, fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                dado.subtitulo, style = TextStyle(
                    fontSize = 12.sp, color = BackgroundColor, fontWeight = FontWeight.Medium
                ), modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}
