package br.com.casa_guido.screens.home.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Agendamento
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Highlight
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils


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
                    Utils.getInitials(agendamento.nome),
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

                Row {
                    Text(
                        agendamento.horario,
                        style = TextStyle(),
                        fontSize = 12.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.size(10.dp))
                    Text(
                        agendamento.tipo,
                        style = TextStyle(),
                        fontSize = 12.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }


        }
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .width(100.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (agendamento.status) BackgroundColor else Highlight),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                if(agendamento.status) "CONFIRMADO" else "PENDENTE",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = if(agendamento.status) Color.White else GreenBlack,
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}
