package br.com.casa_guido.screens.cadastro.formularios.quimio

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Cirurgia
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.RadioButtonComLabel
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Quimio(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
) {


    var toggleDataPickerCirurgia by remember {
        mutableStateOf(false)
    }

    val listaCirurgias = remember { mutableStateListOf<Cirurgia>() }


    var cirurgiaEdicao by remember {
        mutableStateOf(Cirurgia())
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Paragraph)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    onCollapse()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "5. Quimio",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de quimioterapias do paciente",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start
                    )
                )
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Main)
            .animateContentSize()
            .padding(vertical = 10.dp)
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selecionadoQuimio by remember {
                mutableIntStateOf(0)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    Modifier
                        .size(60.dp, 35.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Paragraph),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Quimio",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(Modifier.width(10.dp))

                RadioButtonComLabel(
                    label = "Sim",
                    selected = selecionadoQuimio == 1,
                    onClickListener = {
                        selecionadoQuimio = 1
                    }
                )
                RadioButtonComLabel(
                    label = "Não",
                    selected = selecionadoQuimio == 2,
                    onClickListener = {
                        selecionadoQuimio = 2
                    }
                )
            }

            AnimatedVisibility(
                visible = selecionadoQuimio == 1,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = shrinkVertically() + fadeOut(),
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                ) {
                    DataPicker(
                        modifier = Modifier.fillMaxWidth(.5f),
                        showDataPicker = toggleDataPickerCirurgia,
                        valorPreenchido = cirurgiaEdicao.dataQuimioInicio,
                        titulo = "Data inicio",
                        onCancelar = {
                            toggleDataPickerCirurgia = false
                        },
                        onChange = {
                            cirurgiaEdicao = cirurgiaEdicao.copy(
                                dataQuimioInicio = it
                            )
                        },
                        onClick = {
                            toggleDataPickerCirurgia = true
                        },
                    )


                    DataPicker(
                        modifier = Modifier.weight(1f),
                        showDataPicker = toggleDataPickerCirurgia,
                        valorPreenchido = cirurgiaEdicao.dataQuimioUltima,
                        titulo = "Data fim",
                        onCancelar = {
                            toggleDataPickerCirurgia = false
                        },
                        onChange = {
                            cirurgiaEdicao = cirurgiaEdicao.copy(
                                dataQuimioUltima = it
                            )
                        },
                        onClick = {
                            toggleDataPickerCirurgia = true
                        },
                    )
                }

            }
        }
    }

}



/**/