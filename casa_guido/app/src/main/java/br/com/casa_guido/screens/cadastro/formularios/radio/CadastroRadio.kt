package br.com.casa_guido.screens.cadastro.formularios.radio

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Radio
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.RadioButtonComLabel
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
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
                    "$numeroTela. Radio",
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
            var selecionadoRadio by remember {
                mutableIntStateOf(0)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                RadioButtonMultOptValores(
                    opcoesLista = listOf("Sim" to 1, "Não" to 0),
                    modifier = Modifier.fillMaxWidth(.9f),
                    selected = selecionadoRadio,
                    labelTitulo = "Quimio",
                ) {
                    selecionadoRadio = it
                }
            }

            AnimatedVisibility(
                visible = selecionadoRadio == 1,
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
                        showDataPicker = state.toggleDataPickerInicioRadio,
                        valorPreenchido = state.radioEdicao.dataInicio,
                        titulo = "Data inicio",
                        onCancelar = {
                            viewModel.toggleDataPickerRadioInicio()
                        },
                        onChange = {
                            Log.i("RadioCadastro 0", "onChange: $it")
                            viewModel.onChangeRadio(it)
                        },
                        onClick = {
                            viewModel.toggleDataPickerRadioInicio()
                        },
                    )


                    DataPicker(
                        modifier = Modifier.weight(1f),
                        showDataPicker = state.toggleDataPickerFimRadio,
                        valorPreenchido = state.radioEdicao.dataUltimaSessao,
                        titulo = "Data fim",
                        onCancelar = {
                            viewModel.toggleDataPickerRadioFim()
                        },
                        onChange = {
                            Log.i("RadioCadastro 1", "onChange: $it")
                            viewModel.onChangeRadioFim(it)
                        },
                        onClick = {
                            viewModel.toggleDataPickerRadioFim()
                        },
                    )
                }

            }

            Button(
                onClick = {
                    if (state.radioEdicao.dataInicio.isNotEmpty() && state.radioEdicao.dataUltimaSessao.isNotEmpty()) {
                        onChangeCampo(
                            CamposRadio.ADD_RADIO,
                            state.radioEdicao
                        )
                        viewModel.newRadio()
                        viewModel.onChangeRadio(Utils.formatData(LocalDate.now())!!)
                        viewModel.onChangeRadioFim(Utils.formatData(LocalDate.now())!!)

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
                    text = "Adicionar Radio",
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
                        text = "Lista de Radios",
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

