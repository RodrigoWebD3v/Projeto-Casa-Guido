package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import android.os.Build
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
import androidx.compose.foundation.layout.PaddingValues
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
import br.com.casa_guido.screens.Cirurgia
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroCirurgia(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposCirurgia, Cirurgia) -> Unit,
    listaCirurgias: List<Cirurgia>,
    numeroTela: Int
) {

    val viewModel = koinViewModel<CirurgiaViewModel>()
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
                    "$numeroTela. Cirurgia",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de cirurgias do paciente",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldSimples(
                        nomeCampo = "Nome da cirurgia",
                        valorPreenchido = state.cirurgiaEdicao.nome,
                        placeholder = "Nefrectomia",
                        onChange = {
                            viewModel.onChangeCirurgiaEdicao(
                                state.cirurgiaEdicao.copy(
                                nome = it
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.6f),
                        paddingValues = PaddingValues(start = 20.dp, end = 0.dp)
                    )


                    DataPicker(
                        showDataPicker = state.toggleDataPickerCirurgia,
                        valorPreenchido = state.cirurgiaEdicao.data,
                        titulo = "Data da cirurgia",
                        onCancelar = {
                            viewModel.toggleDatePickerCirurgia()
                        },
                        onChange = {
                            viewModel.onChangeCirurgiaEdicao(
                                state.cirurgiaEdicao.copy(
                                    data = it
                                )
                            )
                        },
                        onClick = {
                            viewModel.toggleDatePickerCirurgia()
                        },
                    )
                }

            }

            Row {
                TextFieldSimples(
                    nomeCampo = "CID",
                    valorPreenchido = state.cirurgiaEdicao.cid,
                    placeholder = "",
                    onChange = {
                        viewModel.onChangeCirurgiaEdicao(
                            state.cirurgiaEdicao.copy(
                                cid = it
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(.6f),
                    paddingValues = PaddingValues(start = 20.dp, end = 0.dp)
                )

                Button(
                    onClick = {
                        if (state.cirurgiaEdicao.nome.isNotEmpty()) {
                            onChangeCampo(
                                CamposCirurgia.ADD_CIRURGIA,
                                state.cirurgiaEdicao
                            )
                            viewModel.onChangeCirurgiaEdicao( Cirurgia(data = Utils.formatData(LocalDate.now())!!))
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
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
                        text = "Adicionar Cirurgia",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = br.com.casa_guido.ui.theme.Button,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
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
                        text = "Lista de Cirurgias",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Text(
                        text = "Quantidade: ${listaCirurgias.size}",
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
                            .heightIn(min = 0.dp, max = 450.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp)
                        ) {
                            listaCirurgias.forEachIndexed { index, item ->
                                ItemCirurgia(
                                    nomeCirurgia = item.nome,
                                    data = item.data,
                                    cid = item.cid
                                ) {
                                    onChangeCampo(
                                        CamposCirurgia.REMOVE_CIRURGIA,
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


