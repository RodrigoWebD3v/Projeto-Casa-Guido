package br.com.casa_guido.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataPicker(
    modifier: Modifier = Modifier,
    showDataPicker: Boolean,
    onCancelar: () -> Unit,
    onChange: (String) -> Unit,
    onClick: () -> Unit,
    valorPreenchido: String,
    titulo: String,
    pickedDatePaciente: LocalDate = LocalDate.now()
) {

    var pickedDate by remember {
        mutableStateOf(pickedDatePaciente)
    }

    val formattedDate by remember {
        derivedStateOf {
            Utils.formatData(
                pickedDate
            )
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
            formattedDate!!
        )
    }

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            titulo, style = TextStyle(
                fontSize = 14.sp,
                color = GreenBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Paragraph,
                    shape = RoundedCornerShape(5.dp)
                )
                .height(45.dp)
                .clickable {
                    onClick()
                }
                .background(
                    color = Paragraph,
                    shape = RoundedCornerShape(5.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if(valorPreenchido == "") formattedDate!! else valorPreenchido,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = BackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                ),
            )
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        onCloseRequest = {

        },

        buttons = {
            positiveButton(
                text = "Selecionar",
                textStyle = TextStyle(color = Main)
            ) {
                onCancelar()
            }
            negativeButton(
                text = "Cancelar",
                textStyle = TextStyle(color = Main)
            ) {
                onCancelar()
            }
        },

        backgroundColor = BackgroundColor

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
                formattedDate!!
            )
        }
    }

}