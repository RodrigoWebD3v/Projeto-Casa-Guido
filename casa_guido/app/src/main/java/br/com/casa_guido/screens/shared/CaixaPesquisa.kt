package br.com.casa_guido.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack

@Composable
fun CaixaPesquisa(
    modifier: Modifier = Modifier,
    textoParam: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    var hasFocus by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        BasicTextField(
            value = textoParam,
            onValueChange = { onValueChange(it) },
            textStyle = LocalTextStyle.current.copy(
                color = GreenBlack,
                fontSize = 16.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            cursorBrush = SolidColor(BackgroundColor),
            modifier = Modifier
                .onFocusChanged { focusState ->
                    if (focusState.isFocused && !hasFocus) {
                        onValueChange("")
                        hasFocus = true
                    }
                    if (!focusState.isFocused) {
                        hasFocus = false
                    }
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone de busca",
                        tint = GreenBlack,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    if (!hasFocus && textoParam.isEmpty()) {
                        Text(
                            text = "Pesquisar",
                            color = GreenBlack,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
