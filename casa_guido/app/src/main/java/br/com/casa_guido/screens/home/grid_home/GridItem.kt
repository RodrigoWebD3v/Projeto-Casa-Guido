package br.com.casa_guido.screens.home.grid_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main


@Composable
fun GridItem(dado: GridItemData) {
    Card(
        modifier = Modifier.fillMaxSize().shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
        ),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Main
        ),
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