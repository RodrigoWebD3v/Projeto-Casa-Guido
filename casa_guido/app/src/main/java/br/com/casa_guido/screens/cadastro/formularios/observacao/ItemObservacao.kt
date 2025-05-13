package br.com.casa_guido.screens.cadastro.formularios.observacao

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun ItemObservacao(modifier: Modifier = Modifier,textoObservacao: String, onClickListner:() -> Unit, onExcluir: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Paragraph)
            .padding(12.dp)
            .clickable {
                onClickListner()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            textoObservacao,
            style = TextStyle(
                color = GreenBlack,
                fontSize = 14.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Red.copy(alpha = 0.8f))
                .clickable { onExcluir() },
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Text("X", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}