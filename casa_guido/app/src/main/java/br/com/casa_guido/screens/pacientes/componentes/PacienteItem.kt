package br.com.casa_guido.screens.pacientes.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Highlight
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils


@Composable
fun PacienteItem(paciente: Paciente, onUpId: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable {
                onUpId(
                    paciente.id
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
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
                        Utils.getInitials(paciente.pessoa.nome),
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
                        paciente.pessoa.nome,
                        style = TextStyle(),
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        paciente.pessoa.telefone,
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
                    .width(75.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (paciente.status) BackgroundColor else Highlight),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    if (paciente.status) "ATIVO" else "INATIVO",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = if (paciente.status) Color.White else GreenBlack,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }
        }

    }
}

@Preview
@Composable
private fun PacienteItemPrev() {
    PacienteItem(
        Paciente(
            status = true
        ),
        onUpId = TODO()
    )
}

@Preview
@Composable
private fun PacienteItemPrev2() {
    PacienteItem(
        Paciente(
            status = false
        ),
        onUpId = TODO(),
    )
}