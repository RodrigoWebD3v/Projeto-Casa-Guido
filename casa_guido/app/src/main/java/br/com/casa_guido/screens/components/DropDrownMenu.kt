package br.com.casa_guido.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import java.util.UUID


data class DropDownMenuItem(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val icone: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    opcoes: List<DropDownMenuItem> = emptyList(),
    valorPreenchido: DropDownMenuItem? = null,
    onSelected: (DropDownMenuItem) -> Unit
) {

    var selectedItem by remember { mutableStateOf(opcoes.first()) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        valorPreenchido?.let {
            valorPreenchido
        } ?: opcoes.first()
    }

    Box(
        modifier = modifier,
        //contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            },
        ) {
            TextField(
                value = selectedItem.nome,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                label = { Text("Selecione uma opcao") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = BackgroundColor,
                    focusedContainerColor = BackgroundColor,
                    unfocusedContainerColor = BackgroundColor
                ),
                // shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                },
                modifier = Modifier.background(BackgroundColor),
                //shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ) {
                opcoes.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.nome) },
                        onClick = {
                            selectedItem = item
                            isExpanded = false
                            onSelected(item)
                        },
                        leadingIcon = {
                            if (
                                item == selectedItem
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Ícone de cadastro",
                                    tint = Main
                                )
                            }
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

    }
}