package br.com.casa_guido.screens.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun CaixaPesquisa(
    modifier: Modifier = Modifier,
    textoParam: String = "",
    onValueChange: (String) -> Unit = {}
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),

        value = textoParam,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            color = Color.Black
        ),
        placeholder = {
            Text(
                text = "Buscar pacientes...",
                style = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right, color = Color.Black
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Icone de busca",
                tint = Color.Black
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Paragraph,
            unfocusedIndicatorColor = Paragraph,
            cursorColor = BackgroundColor,
            backgroundColor = Color.Transparent,
            textColor = GreenBlack
        ),
        shape = RoundedCornerShape(30.dp),
        maxLines = 1
    )
}