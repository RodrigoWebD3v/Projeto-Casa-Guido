package br.com.casa_guido.screens.cadastro.components.CamposPaciente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun TextFieldSimples(
    modifier: Modifier = Modifier,
    nomeCampo: String,
    valorPreenchido: String,
    onChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            nomeCampo, style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )
        )

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = valorPreenchido,
            onValueChange = {
                onChange(it)
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left,
                fontSize = 18.sp,
                color = BackgroundColor
            ),
            placeholder = {
                Text(
                    text = nomeCampo,
                    style = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Right, color = BackgroundColor
                    )
                )
            },
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Paragraph,
                unfocusedIndicatorColor = Paragraph,
                cursorColor = BackgroundColor,
                backgroundColor = Color.Transparent,
                textColor = GreenBlack
            ),
            shape = RoundedCornerShape(5.dp),
            maxLines = 1
        )
    }


}