package br.com.casa_guido.screens.cadastro.formularios.radioterapia

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.Radio
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RadioCadastro(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposRadio, Radio) -> Unit,
    listaRadios: List<Radio>,
    numeroTela: Int
) {
    val viewModel = koinViewModel<RadioViewModel>()
    val state by viewModel.uiState.collectAsState()


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Paragraph)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "$numeroTela. Radioterapia",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de radioterapias do paciente",
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
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                Modifier.fillMaxWidth(),
            ) {
                DataPicker(
                    showDataPicker = state.toggleDataPickerInicioRadio,
                    valorPreenchido = state.radioEdicao.dataInicio,
                    titulo = "Data início",
                    onCancelar = {
                        viewModel.toggleDataPickerRadioInicio()
                    },
                    onChange = {
                        viewModel.onChangeRadio(it)
                    },
                    onClick = {
                        viewModel.toggleDataPickerRadioInicio()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                        .padding(end = 10.dp),
                )


                DataPicker(
                    showDataPicker = state.toggleDataPickerFimRadio,
                    valorPreenchido = state.radioEdicao.dataUltimaSessao,
                    titulo = "Data fim",
                    onCancelar = {
                        viewModel.toggleDataPickerRadioFim()
                    },
                    onChange = {
                        viewModel.onChangeRadioFim(it)
                    },
                    onClick = {
                        viewModel.toggleDataPickerRadioFim()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                    )
            }


            Button(
                onClick = {
                    if (state.radioEdicao.dataInicio.isNotEmpty() && state.radioEdicao.dataUltimaSessao.isNotEmpty()) {
                        onChangeCampo(
                            CamposRadio.ADD_RADIO,
                            state.radioEdicao
                        )
                        viewModel.newRadio()
                        viewModel.onChangeRadio(Utils.formataDataPadraoBr(LocalDate.now())!!)
                        viewModel.onChangeRadioFim(Utils.formataDataPadraoBr(LocalDate.now())!!)

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BackgroundColor),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Adicionar Radioterapia",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = br.com.casa_guido.ui.theme.Button,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Paragraph,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(12.dp)
                        .clickable {
                            viewModel.toggleList()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Lista de Radioterapias",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Text(
                        text = "Quantidade: ${listaRadios.size}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = GreenBlack,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(start = 10.dp)
                            .clickable {
                                viewModel.toggleList()
                            },
                        imageVector = if (state.onVisibleList) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "Seta para baixo",
                        tint = Color.Black
                    )
                }
                AnimatedVisibility(
                    visible = state.onVisibleList,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    exit = shrinkVertically() + fadeOut(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 0.dp, max = 400.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp)
                        ) {
                            listaRadios.forEachIndexed { index, item ->
                                ItemRadio(
                                    item.dataInicio,
                                    item.dataUltimaSessao
                                ) {
                                    onChangeCampo(
                                        CamposRadio.REMOVE_RADIO,
                                        item
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}

