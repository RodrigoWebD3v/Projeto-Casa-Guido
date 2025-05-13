package br.com.casa_guido.screens.cadastro.formularios.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun ItemRadio(
    dataInicio: String,
    dataFim: String,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column {
                Row {
                    Text(
                        text = "Início:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = GreenBlack
                    )

                    Text(
                        text = dataInicio,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = GreenBlack,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
                Row {
                    Text(
                        text = "Fim:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = GreenBlack,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

                    Text(
                        text = dataFim,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = GreenBlack,
                    )
                }
            }
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