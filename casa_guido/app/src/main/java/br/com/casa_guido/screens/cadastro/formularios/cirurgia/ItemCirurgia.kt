package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun ItemCirurgia(
    nomeCirurgia: String,
    data: String,
    onExcluir: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Paragraph)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = nomeCirurgia,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Data: $data",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Red.copy(alpha = 0.8f))
                .clickable { onExcluir() },
            contentAlignment = Alignment.Center
        ) {
            Text("X", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}