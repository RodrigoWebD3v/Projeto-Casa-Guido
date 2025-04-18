package br.com.casa_guido.screens.shared

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun BotaoPersonalizadoComIcones(
    modifier: Modifier = Modifier,
    iconeBotao: ImageVector,
    color: Color,
    onClick: () -> Unit,
    titulo: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                onClick = {
                    onClick()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .background(Paragraph)
                .padding(vertical = 3.dp)
                .height(40.dp)
                .fillMaxWidth(.6f),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Text(
                titulo, style = TextStyle(
                    fontSize = 18.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
        }

        Box(
            Modifier
                .background(color)
                .padding(vertical = 3.dp)
                .height(40.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                iconeBotao,
                contentDescription = "Salvar",
                tint = Main,
            )
        }
    }
}