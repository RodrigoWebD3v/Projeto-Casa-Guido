package br.com.casa_guido.screens.cadastro.formularios.conjuge

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.customizacao.VisualTransformationCustom
import br.com.casa_guido.screens.Pessoa
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.endereco.ModalEndereco
import br.com.casa_guido.screens.shared.BotaoPersonalizadoComIcones
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.RadioButtonComLabelWidthIn
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.Alert
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroConjuge(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposConjuge, String) -> Unit,
    conjuge: Pessoa,
    onChangeEndereco: (CamposEndereco, String) -> Unit,
    numeroTela: Int
) {

    var openBottomSheet by remember {
        mutableStateOf(
            false
        )
    }

    var color by remember {
        mutableStateOf(GreenBlack)
    }

    var iconeBotao by remember {
        mutableStateOf(Icons.Default.Check)
    }

    LaunchedEffect(conjuge) {
        color = if (!(conjuge.endereco.cep.isEmpty()
                    || conjuge.endereco.numero.isEmpty()
                    || conjuge.endereco.logradouro.isEmpty()
                    || conjuge.endereco.bairro.isEmpty()
                    || conjuge.endereco.referencia.isEmpty()
                    || conjuge.endereco.localidade.isEmpty()
                    || conjuge.endereco.uf.isEmpty())
        ) GreenBlack else Alert

        iconeBotao = if (!(conjuge.endereco.cep.isEmpty()
                    || conjuge.endereco.numero.isEmpty()
                    || conjuge.endereco.logradouro.isEmpty()
                    || conjuge.endereco.bairro.isEmpty()
                    || conjuge.endereco.referencia.isEmpty()
                    || conjuge.endereco.localidade.isEmpty()
                    || conjuge.endereco.uf.isEmpty())
        ) Icons.Default.Check else Icons.Default.Close
    }


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
                    "$numeroTela. Conjuge",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações do conjuge",
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
            .padding(bottom = 10.dp)
            .verticalScroll(rememberScrollState()),
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
                    valorPreenchido = conjuge.nome,
                    onChange = {
                        onChangeCampo(
                            CamposConjuge.NOME_CONJUGE,
                            it
                        )
                    },
                    placeholder = ""
                )

                var dataPickerNascimentoShow by remember {
                    mutableStateOf(false)
                }


                Row {
                    TextFieldSimples(
                        nomeCampo = "Cpf",
                        valorPreenchido = conjuge.cpf,
                        visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                        placeholder = "",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.CPF_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Rg",
                        valorPreenchido = conjuge.rg,
                        visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                        placeholder = "",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.RG_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {
                    TextFieldSimples(
                        nomeCampo = "Naturalidade",
                        valorPreenchido = conjuge.naturalidade,
                        visualTransformation = VisualTransformation.None,
                        placeholder = "",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.NATURALIDADE_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Escolaridade",
                        valorPreenchido = conjuge.escolaridade,
                        visualTransformation = VisualTransformation.None,
                        placeholder = "",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.ESCOLARIDADE_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {

                    DataPicker(
                        showDataPicker = dataPickerNascimentoShow,
                        valorPreenchido = conjuge.dataNascimento,
                        titulo = "Data de Nascimento",
                        onCancelar = {
                            dataPickerNascimentoShow = false
                        },
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.DATA_NASCIMENTO_CONJUGE,
                                it
                            )
                        },
                        onClick = {
                            dataPickerNascimentoShow = true
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )



                    TextFieldSimples(
                        nomeCampo = "Celular",
                        valorPreenchido = conjuge.telefone,
                        visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                        placeholder = "(48) 99963-9821",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.TELEFONE_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                TextFieldSimples(
                    nomeCampo = "Cartão do sus",
                    valorPreenchido = conjuge.cartaoSus,
                    visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                    placeholder = "567 8901 2345 6789",
                    onChange = {
                        onChangeCampo(
                            CamposConjuge.CARTAO_SUS,
                            it
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.padding(start = 20.dp),
                    verticalAlignment = Alignment.Bottom
                ) {

                    BotaoPersonalizadoComIcones(
                        iconeBotao = iconeBotao,
                        color = color,
                        onClick = {
                            openBottomSheet = true
                        },
                        titulo = "Endereço",
                        modifier = Modifier.fillMaxWidth(.6f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Salário",
                        valorPreenchido = conjuge.salario,
                        visualTransformation = VisualTransformation.None,
                        keyboardType = KeyboardType.Number,
                        placeholder = "",
                        onChange = {
                            onChangeCampo(
                                CamposConjuge.SALARIO_CONJUGE,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

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
                                selected = conjuge.estadoCivil == 1,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        1.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Casado",
                                selected = conjuge.estadoCivil == 2,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        2.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "União Estavel",
                                selected = conjuge.estadoCivil == 3,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        3.toString()
                                    )
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
                                selected = conjuge.estadoCivil == 4,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        4.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Separado",
                                selected = conjuge.estadoCivil == 5,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        5.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Outro",
                                selected = conjuge.estadoCivil == 6,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                        6.toString()
                                    )
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
                                selected = conjuge.situacaoProfissional == 1,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        1.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Desempregado",
                                selected = conjuge.situacaoProfissional == 2,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        2.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Autonomo",
                                selected = conjuge.situacaoProfissional == 3,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        3.toString()
                                    )
                                }
                            )
                            RadioButtonComLabelWidthIn(
                                label = "Pensionista",
                                selected = conjuge.situacaoProfissional == 4,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        4.toString()
                                    )
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
                                selected = conjuge.situacaoProfissional == 5,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        5.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Diarista",
                                selected = conjuge.situacaoProfissional == 6,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        6.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Pensionista",
                                selected = conjuge.situacaoProfissional == 7,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        7.toString()
                                    )
                                }
                            )

                            RadioButtonComLabelWidthIn(
                                label = "Outro",
                                selected = conjuge.situacaoProfissional == 8,
                                onClickListener = {
                                    onChangeCampo(
                                        CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                        8.toString()
                                    )
                                }
                            )
                        }
                    }

                    ModalEndereco(
                        openBottomSheet = openBottomSheet,
                        pessoa = conjuge,
                        onChange = { campo, valor ->
                            onChangeEndereco(
                                campo, valor
                            )
                        },
                        onDismiss = {
                            openBottomSheet = false
                        },
                    )

                }

            }
        }
    }
}

