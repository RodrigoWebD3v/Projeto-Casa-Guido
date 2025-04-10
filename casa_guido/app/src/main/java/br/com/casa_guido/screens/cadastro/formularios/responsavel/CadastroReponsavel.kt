package br.com.casa_guido.screens.cadastro.formularios.responsavel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import br.com.casa_guido.customizacao.VisualTransformationCustom
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.RadioButtonComLabelWidthIn
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroResponsavel(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
) {
    var selectionadoEstadoCivil by remember {
        mutableIntStateOf(0)
    }

    var selecionadoSitProfissional by remember {
        mutableIntStateOf(0)
    }

    var toggleDataPickerCirurgia by remember {
        mutableStateOf(false)
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
                    "6. Responsavel",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações do responsavel",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {


                TextFieldSimples(
                    nomeCampo = "Nome Completo",
                    valorPreenchido = "",
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.CEP,
                            it
                        )
                    },
                    placeholder = "Rodrigo Cardoso"
                )

                var dataPickerNascimentoShow by remember {
                    mutableStateOf(false)
                }

                DataPicker(
                    showDataPicker = dataPickerNascimentoShow,
                    valorPreenchido = "",
                    titulo = "Data de Nascimento",
                    onCancelar = {
                        dataPickerNascimentoShow = false
                    },
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.CEP,
                            it
                        )
                    },
                    onClick = {
                        dataPickerNascimentoShow = true
                    },
                )


                Row {
                    TextFieldSimples(
                        nomeCampo = "Cpf",
                        valorPreenchido = "",
                        visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                        placeholder = "111.111.111.99",
                        onChange = {
                            onChangeCampo(
                                CamposEndereco.CEP,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Rg",
                        valorPreenchido = "",
                        visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                        placeholder = "11.111.111-9",
                        onChange = {
                            onChangeCampo(
                                CamposEndereco.CEP,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {
                    TextFieldSimples(
                        nomeCampo = "Naturalidade",
                        valorPreenchido = "",
                        visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                        placeholder = "Brasileiro",
                        onChange = {
                            onChangeCampo(
                                CamposEndereco.CEP,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Escolaridade",
                        valorPreenchido = "",
                        visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                        placeholder = "8 série",
                        onChange = {
                            onChangeCampo(
                                CamposEndereco.CEP,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {

                    DataPicker(
                        showDataPicker = toggleDataPickerCirurgia,
                        valorPreenchido = "",
                        titulo = "Data da cirurgia",
                        onCancelar = {
                            toggleDataPickerCirurgia = false
                        },
                        onChange = {

                        },
                        onClick = {
                            toggleDataPickerCirurgia = true
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )


                    TextFieldSimples(
                        nomeCampo = "Celular",
                        valorPreenchido = "",
                        visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                        placeholder = "(48) 99963-9821",
                        onChange = {
                            onChangeCampo(
                                CamposEndereco.CEP,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                TextFieldSimples(
                    nomeCampo = "Cartão do sus",
                    valorPreenchido = "",
                    visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                    placeholder = "567 8901 2345 6789",
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.CEP,
                            it
                        )
                    },
                    paddingValues = PaddingValues(start = 20.dp, end = 10.dp),
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(bottom = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Paragraph),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Estado Civil",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = BackgroundColor,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                    Spacer(Modifier.width(10.dp))

                    Row(
                        Modifier.height(IntrinsicSize.Min)
                    ) {
                        Column {
                            RadioButtonComLabelWidthIn(
                                label = "Solteiro",
                                selected = selectionadoEstadoCivil == 1,
                                onClickListener = {
                                    selectionadoEstadoCivil = 1
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Casado",
                                selected = selectionadoEstadoCivil == 2,
                                onClickListener = {
                                    selectionadoEstadoCivil = 2
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "União Estavel",
                                selected = selectionadoEstadoCivil == 3,
                                onClickListener = {
                                    selectionadoEstadoCivil = 3
                                }
                            )
                        }

                        VerticalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(BackgroundColor)
                        )
                        Column(
                            Modifier.padding(start = 10.dp)
                        ) {
                            RadioButtonComLabelWidthIn(
                                label = "Viúvo",
                                selected = selectionadoEstadoCivil == 4,
                                onClickListener = {
                                    selectionadoEstadoCivil = 4
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Separado",
                                selected = selectionadoEstadoCivil == 5,
                                onClickListener = {
                                    selectionadoEstadoCivil = 5
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Outro",
                                selected = selectionadoEstadoCivil == 6,
                                onClickListener = {
                                    selectionadoEstadoCivil = 6
                                }
                            )
                        }
                    }

                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(bottom = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Paragraph),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Situacao Profissional",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = BackgroundColor,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                    Spacer(Modifier.width(10.dp))

                    Row(
                        Modifier.height(IntrinsicSize.Min)
                    ) {
                        Column {
                            RadioButtonComLabelWidthIn(
                                label = "Empregado",
                                selected = selecionadoSitProfissional == 1,
                                onClickListener = {
                                    selecionadoSitProfissional = 1
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Desempregado",
                                selected = selecionadoSitProfissional == 2,
                                onClickListener = {
                                    selecionadoSitProfissional = 2
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Autonomo",
                                selected = selecionadoSitProfissional == 3,
                                onClickListener = {
                                    selecionadoSitProfissional = 3
                                }
                            )
                            RadioButtonComLabelWidthIn(
                                label = "Pensionista",
                                selected = selecionadoSitProfissional == 4,
                                onClickListener = {
                                    selecionadoSitProfissional = 4
                                }
                            )
                        }

                        VerticalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(BackgroundColor)
                        )

                        Column(
                            Modifier.padding(start = 10.dp)
                        ) {
                            RadioButtonComLabelWidthIn(
                                label = "Aposentado",
                                selected = selecionadoSitProfissional == 5,
                                onClickListener = {
                                    selecionadoSitProfissional = 5
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Diarista",
                                selected = selecionadoSitProfissional == 6,
                                onClickListener = {
                                    selecionadoSitProfissional = 6
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Pensionista",
                                selected = selecionadoSitProfissional == 7,
                                onClickListener = {
                                    selecionadoSitProfissional = 7
                                }
                            )

                            TextFieldSimples(
                                nomeCampo = "Salário",
                                valorPreenchido = "",
                                visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                                placeholder = "1.230",
                                onChange = {
                                    onChangeCampo(
                                        CamposEndereco.CEP,
                                        it
                                    )
                                },
                                paddingValues = PaddingValues(0.dp, 0.dp),
                                modifier = Modifier.fillMaxWidth(.7f)
                            )
                        }

                    }
                }

            }
        }
    }
}