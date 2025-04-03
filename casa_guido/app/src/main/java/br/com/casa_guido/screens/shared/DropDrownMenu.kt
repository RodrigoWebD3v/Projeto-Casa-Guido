package br.com.casa_guido.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.casa_guido.ui.theme.Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(modifier: Modifier = Modifier) {

    val lista = listOf(
        "Nome",
        "Idade",
        "Data de Nascimento",
        "Sexo",
        "Telefone",
        "Endereço",
        "Cidade",
        "Estado",
        "CEP"
    )

    var selectedItem by remember { mutableStateOf(lista.first()) }
    var isExpanded by remember { mutableStateOf(false) }


    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        ) {
            androidx.compose.material3.TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.menuAnchor(),
                label = { Text("Selecione um campo") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                lista.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedItem = item
                            isExpanded = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonAddAlt,
                                contentDescription = "Ícone de cadastro",
                                tint = Main
                            )
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

    }
}