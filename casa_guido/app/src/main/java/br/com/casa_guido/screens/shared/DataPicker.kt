package br.com.casa_guido.screens.shared

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Paragraph
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataPicker(
    modifier: Modifier = Modifier,
    showDataPicker: Boolean,
    onChange: (String) -> Unit,
    pickedDatePaciente: LocalDate = LocalDate.now()
) {

    var pickedDate by remember {
        mutableStateOf(pickedDatePaciente)
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .format(pickedDate)
        }
    }

    val dateDialogState = rememberMaterialDialogState()

    LaunchedEffect(showDataPicker) {
        if (showDataPicker) {
            dateDialogState.show()
        } else {
            dateDialogState.hide()
        }
    }

    LaunchedEffect(Unit){
        onChange(
            formattedDate
        )
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Selecionar") {

            }
            negativeButton(text = "Cancelar")
        },

        ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Selecione uma data",
            colors = com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults.colors(
                headerBackgroundColor = BackgroundColor,
                headerTextColor = Color.White,
                calendarHeaderTextColor = Color.LightGray,
                dateActiveBackgroundColor = Paragraph,
                dateActiveTextColor = Color.White,
                dateInactiveTextColor = Color.Gray,
                dateInactiveBackgroundColor = Color.Transparent,
            )
        ) {
            pickedDate = it
            onChange(
                formattedDate
            )
        }
    }

}