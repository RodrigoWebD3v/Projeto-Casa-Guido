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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Cirurgia
import br.com.casa_guido.screens.Paciente
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
fun Cirurgia(
    modifier: Modifier = Modifier,
    selectedItem: Boolean,
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
) {


    var toggleDataPickerCirurgia by remember {
        mutableStateOf(false)
    }

    val listaCirurgias = remember {
        mutableStateListOf(Cirurgia())
    }

    var cirurgia by remember {
        mutableStateOf(Cirurgia())
    }

    Card(
        modifier
            .fillMaxWidth(.95f)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onCollapse()
            }

    ) {
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
                        "4. Cirurgias",
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
                Icon(
                    imageVector = if (selectedItem) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Adicionar",
                    tint = GreenBlack,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(20.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = (selectedItem),
            enter = expandVertically(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = shrinkVertically() + fadeOut(),

            ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Main)
                    .animateContentSize()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var cirurgiaNome by remember {
                        mutableStateOf("")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        TextFieldSimples(
                            nomeCampo = "Nome da cirurgia",
                            valorPreenchido = cirurgia.nome,
                            onChange = {
                                cirurgia = cirurgia.copy(
                                    nome = it
                                )
                            }
                        )


                        DataPicker(
                            showDataPicker = toggleDataPickerCirurgia,
                            valorPreenchido = "",
                            titulo = "Data da cirurgia",
                            onCancelar = {
                                toggleDataPickerCirurgia = false
                            },
                            onChange = {
                                cirurgia = cirurgia.copy(
                                    data = it
                                )
                            },
                            onClick = {
                                toggleDataPickerCirurgia = true
                            },
                        )

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



                        if (selecionadoQuimio == 1) {

                            Row(
                                Modifier.fillMaxWidth(),
                            ) {
                                DataPicker(
                                    modifier = Modifier.fillMaxWidth(.5f),
                                    showDataPicker = false,
                                    valorPreenchido = "",
                                    titulo = "Data inicio",
                                    onCancelar = {
                                        toggleDataPickerCirurgia = false
                                    },
                                    onChange = {
                                        onChangeCampo(
                                            CamposEndereco.CEP,
                                            it
                                        )
                                    },
                                    onClick = {
                                        toggleDataPickerCirurgia = true
                                    },
                                )


                                DataPicker(
                                    modifier = Modifier.fillMaxWidth(.5f),
                                    showDataPicker = false,
                                    valorPreenchido = "",
                                    titulo = "Data inicio",
                                    onCancelar = {
                                        toggleDataPickerCirurgia = false
                                    },
                                    onChange = {
                                        onChangeCampo(
                                            CamposEndereco.CEP,
                                            it
                                        )
                                    },
                                    onClick = {
                                        toggleDataPickerCirurgia = true
                                    },
                                )
                            }

                        }

                        Button(
                            onClick = {
                                listaCirurgias.add(cirurgia)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(GreenBlack),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenBlack,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Adicionar cirurgia",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                        Card {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                listaCirurgias.forEach { cirurgia ->
                                    Text(
                                        text = "${cirurgia.nome} - ${cirurgia.data}",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Start
                                        )
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


