package br.com.casa_guido.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
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
    paddingValues: PaddingValues = PaddingValues(20.dp,0.dp),
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            nomeCampo,
            style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(
                    width = 1.5.dp,
                    color = Paragraph,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            BasicTextField(
                value = valorPreenchido,
                onValueChange = { onChange(it) },
                textStyle = LocalTextStyle.current.copy(
                    color = GreenBlack,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                cursorBrush = SolidColor(BackgroundColor),
                visualTransformation = visualTransformation,
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (valorPreenchido.isEmpty()) {
                        Text(
                            text = "",
                            color = BackgroundColor,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}
