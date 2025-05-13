package br.com.casa_guido.screens.home.componentes.grid_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


data class GridItemData(
    val title: String, val valorPrincipal: String, val subtitulo: String
)


@Composable
fun GridScreen(
    gridItemsList: List<GridItemData> = emptyList()
) {
    val gridItems = listOf(
        GridItemData("Total de Pacientes", "100", "+12% este mes"),
        GridItemData("Hoje", "12", "3 pentendes"),
        GridItemData("Semana", "42", "+8% vs anterior"),
        GridItemData("Comparecimento", "92%", "+2% este mes")
    )

    val items = gridItemsList

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