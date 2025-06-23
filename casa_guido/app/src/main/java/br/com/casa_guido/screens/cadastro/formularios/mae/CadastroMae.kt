package br.com.casa_guido.screens.cadastro.formularios.mae

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.customizacao.VisualTransformationCustom
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.endereco.ModalEndereco
import br.com.casa_guido.screens.cadastro.formularios.pai.CamposResponsavel
import br.com.casa_guido.screens.components.BotaoPersonalizadoComIcones
import br.com.casa_guido.screens.components.CheckBoxComp
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.escolaridade.Escolaridade
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.Alert
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
                    || conjuge.endereco.localidade.isEmpty()
                    || conjuge.endereco.uf.isEmpty())
        ) GreenBlack else Alert

        iconeBotao = if (!(conjuge.endereco.cep.isEmpty()
                    || conjuge.endereco.numero.isEmpty()
                    || conjuge.endereco.logradouro.isEmpty()
                    || conjuge.endereco.bairro.isEmpty()
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
                    "$numeroTela. Mãe",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações da mãe",
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
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {

                CheckBoxComp(
                    modifier = Modifier.padding(20.dp),
                    labelTitulo = "Resp Principal",
                    selected = conjuge.respPrincipal,
                ) {
                    onChangeCampo(CamposConjuge.RESP_PRINCIPAL, if(it) "1" else "0")
                }


                TextFieldSimples(
                    nomeCampo = "Nome completo",
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
                        nomeCampo = "CPF",
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
                        nomeCampo = "RG",
                        valorPreenchido = conjuge.rg,
                        visualTransformation = VisualTransformation.None ,//VisualTransformationCustom.RgVisualTransformation(),
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
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Escolaridade(
                        pessoa = conjuge,
                        onChangeEscolaridade = { valor1, valor2 ->
                            Log.i(
                                "Cadastro Mãe",
                                "${valor1} - ${valor2}"
                            )
                            onChangeCampo(
                                CamposConjuge.ESCOLARIDADE,
                                valor1.toString()
                            )
                            onChangeCampo(
                                CamposConjuge.SERIE,
                                valor2.toString()
                            )
                        }
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
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 20.dp)
                            .padding(end = 10.dp),
                    )



                    TextFieldSimples(
                        nomeCampo = "Celular",
                        valorPreenchido = conjuge.telefone,
                        visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                        placeholder = "(48) 99963-9821",
                        somenteNumero = true,
                        onChange = {
                            if(it.length <= 11) {
                                onChangeCampo(
                                    CamposConjuge.TELEFONE_CONJUGE,
                                    it
                                )
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                TextFieldSimples(
                    nomeCampo = "Cartão do SUS",
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
                        somenteNumero = true,
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
                    modifier = Modifier.padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RadioButtonMultOptValores(
                        labelTitulo = "Estado Civil",
                        opcoesLista = listOf(
                            "Solteiro" to 0,
                            "Casado" to 1,
                            "União Estavel" to 2,
                            "Viúvo" to 3,
                            "Separado" to 4,
                            "Outro" to 5
                        ),
                        selected = conjuge.estadoCivil,
                        onClickListener = {
                            onChangeCampo(
                                CamposConjuge.ESTADO_CIVIL_CONJUGE,
                                it.toString()
                            )
                        },
                    )

                    RadioButtonMultOptValores(
                        labelTitulo = "Situação Profissional",
                        opcoesLista = listOf(
                            "Empregado" to 0,
                            "Desempregado" to 1,
                            "Autônomo" to 2,
                            "Pensionista" to 3,
                            "Aposentado" to 4,
                            "Diarista" to 5,
                            "Pensionista" to 6,
                            "Outro" to 7
                        ),
                        selected = conjuge.situacaoProfissional,
                        onClickListener = {
                            onChangeCampo(
                                CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE,
                                it.toString()
                            )
                        },
                    )



                }
                TextFieldSimples(
                    nomeCampo = "Profissão",
                    valorPreenchido = conjuge.profissao,
                    placeholder = "",
                    onChange = {
                        onChangeCampo(
                            CamposConjuge.PROFISSAO,
                            it
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

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

