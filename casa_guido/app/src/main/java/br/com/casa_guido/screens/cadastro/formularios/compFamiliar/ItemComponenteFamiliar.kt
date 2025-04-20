package br.com.casa_guido.screens.cadastro.formularios.compFamiliar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun ItemComponenteFamiliar(
    nomeComponente: String,
    parentesco: String,
    onExcluir: () -> Unit,
    onEditar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .heightIn(max = 90.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Paragraph)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = nomeComponente,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = parentesco,
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Row {

//            Box(
//                modifier = Modifier
//                    .size(36.dp)
//                    .clip(RoundedCornerShape(8.dp))
//                    .background(GreenBlack.copy(alpha = 0.8f))
//                    .clickable { onEditar() },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Edit,
//                    contentDescription = "Editar",
//                    tint = Button
//                )
//            }
//
//            Spacer(Modifier.size(10.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Red.copy(alpha = 0.8f))
                    .clickable { onExcluir() },
                contentAlignment = Alignment.Center
            ) {
                Text("X", color = Button, fontWeight = FontWeight.Bold)
            }

        }


    }
}