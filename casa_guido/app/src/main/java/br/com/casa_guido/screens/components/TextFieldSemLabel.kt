package br.com.casa_guido.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun TextFieldSemLabel(
    modifier: Modifier = Modifier,
    nomeCampo: String,
    valorPreenchido: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
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
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
            maxLines = 1,
            visualTransformation = visualTransformation,
        )
    }
}


