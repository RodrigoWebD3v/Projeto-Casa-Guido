package br.com.casa_guido.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun RadioButtonComLabel(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClickListener: () -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(label, style = TextStyle(
            color = GreenBlack,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        ))

        RadioButton(
            modifier = modifier,
            selected = selected,
            onClick = {
                onClickListener()
            },
            enabled = true,
            colors = RadioButtonDefaults.colors(
                selectedColor = Paragraph,
                unselectedColor = Color.Gray
            )
        )
    }
}