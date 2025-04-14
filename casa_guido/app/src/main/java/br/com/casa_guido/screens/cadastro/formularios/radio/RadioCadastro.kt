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
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.RadioButtonComLabel
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
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
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
                .padding(10.dp)
                .clickable {
                    onCollapse()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "6. Radio",
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
                        showDataPicker = state.toggleDataPickerInicioRadio,
                        valorPreenchido = state.radioEdicao.dataInicio,
                        titulo = "Data inicio",
                        onCancelar = {
                            viewModel.toggleDataPickerQuimioInicio()
                        },
                        onChange = {
                            Log.i("QuimioCadastro 0", "onChange: $it")
                            viewModel.onChangeQuimioInicio(it)
                        },
                        onClick = {
                            viewModel.toggleDataPickerQuimioInicio()
                        },
                    )


                    DataPicker(
                        modifier = Modifier.weight(1f),
                        showDataPicker = state.toggleDataPickerFimRadio,
                        valorPreenchido = state.radioEdicao.dataUltimaSessao,
                        titulo = "Data fim",
                        onCancelar = {
                            viewModel.toggleDataPickerQuimioFim()
                        },
                        onChange = {
                            Log.i("QuimioCadastro 1", "onChange: $it")
                            viewModel.onChangeQuimioFim(it)
                        },
                        onClick = {
                            viewModel.toggleDataPickerQuimioFim()
                        },
                    )
                }

            }

            Button(
                onClick = {
                    if (state.radioEdicao.dataInicio.isNotEmpty() && state.radioEdicao.dataUltimaSessao.isNotEmpty()) {
                        viewModel.addQuimio(state.radioEdicao)
                        viewModel.onChangeQuimioInicio(Utils.formatData(LocalDate.now())!!)
                        viewModel.onChangeQuimioFim(Utils.formatData(LocalDate.now())!!)
                        Log.i("QuimioCadastro", "${state.listaRadio.size}")
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
                    text = "Adicionar quimio",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = br.com.casa_guido.ui.theme.Button,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 20.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Paragraph,
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
                        text = "Lista de quimios",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Text(
                        text = "Quantidade: ${state.listaRadio.size}",
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
                            state.listaRadio.forEachIndexed { index, item ->
                                ItemQuimio(
                                    item.dataInicio,
                                    item.dataUltimaSessao
                                ) {
                                    viewModel.RemoveIndex(index)
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}

