package br.com.casa_guido.screens.cadastro.formularios.pai

import android.os.Build
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
fun CadastroResponsavel(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposResponsavel, String) -> Unit,
    responsavel: Pessoa,
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

    LaunchedEffect(responsavel) {
        color = if (!(responsavel.endereco.cep.isEmpty()
                    || responsavel.endereco.numero.isEmpty()
                    || responsavel.endereco.logradouro.isEmpty()
                    || responsavel.endereco.bairro.isEmpty()
                    || responsavel.endereco.referencia.isEmpty()
                    || responsavel.endereco.localidade.isEmpty()
                    || responsavel.endereco.uf.isEmpty())
        ) GreenBlack else Alert

        iconeBotao = if (!(responsavel.endereco.cep.isEmpty()
                    || responsavel.endereco.numero.isEmpty()
                    || responsavel.endereco.logradouro.isEmpty()
                    || responsavel.endereco.bairro.isEmpty()
                    || responsavel.endereco.referencia.isEmpty()
                    || responsavel.endereco.localidade.isEmpty()
                    || responsavel.endereco.uf.isEmpty())
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
                    "$numeroTela. Pai",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações do pai",
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
                    labelTitulo = "Resp Principal?",
                    selected = responsavel.respPrincipal,
                ) {
                    onChangeCampo(CamposResponsavel.RESP_PRINCIPAL, if (it) "1" else "0")
                }

                TextFieldSimples(
                    nomeCampo = "Nome completo",
                    valorPreenchido = responsavel.nome,
                    onChange = {
                        onChangeCampo(
                            CamposResponsavel.NOME_RESPONSAVEL,
                            it
                        )
                    },
                    placeholder = "Rodrigo Cardoso"
                )

                var dataPickerNascimentoShow by remember {
                    mutableStateOf(false)
                }

                Row {
                    TextFieldSimples(
                        nomeCampo = "CPF",
                        valorPreenchido = responsavel.cpf,
                        visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                        placeholder = "111.111.111.99",
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.CPF_RESPONSAVEL,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "RG",
                        valorPreenchido = responsavel.rg,
                        visualTransformation = VisualTransformation.None ,//VisualTransformationCustom.RgVisualTransformation(),
                        placeholder = "11.111.111-9",
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.RG_RESPONSAVEL,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {
                    TextFieldSimples(
                        nomeCampo = "Naturalidade",
                        valorPreenchido = responsavel.naturalidade,
                        visualTransformation = VisualTransformation.None,
                        placeholder = "Brasileiro",
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.NATURALIDADE_RESPONSAVEL,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(1f)
                    )


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Escolaridade(
                        pessoa = responsavel,
                        onChangeEscolaridade = { valor1, valor2 ->
                            onChangeCampo(
                                CamposResponsavel.ESCOLARIDADE,
                                valor1.toString()
                            )
                            onChangeCampo(
                                CamposResponsavel.SERIE,
                                valor2.toString()
                            )
                        }
                    )
                }

                Row {
                    DataPicker(
                        showDataPicker = dataPickerNascimentoShow,
                        valorPreenchido = responsavel.dataNascimento,
                        titulo = "Data de Nascimento",
                        onCancelar = {
                            dataPickerNascimentoShow = false
                        },
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.DATA_NASCIMENTO_RESPONSAVEL,
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
                        valorPreenchido = responsavel.telefone,
                        visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                        placeholder = "(48) 99963-9821",
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.TELEFONE_RESPONSAVEL,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                TextFieldSimples(
                    nomeCampo = "Cartão do sus",
                    valorPreenchido = responsavel.cartaoSus,
                    visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                    placeholder = "567 8901 2345 6789",
                    onChange = {
                        onChangeCampo(
                            CamposResponsavel.CARTAO_SUS,
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
                        valorPreenchido = responsavel.salario,
                        visualTransformation = VisualTransformation.None,
                        keyboardType = KeyboardType.Number,
                        placeholder = "1.230",
                        somenteNumero = true,
                        onChange = {
                            onChangeCampo(
                                CamposResponsavel.SALARIO_RESPONSAVEL,
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
                        selected = responsavel.estadoCivil,
                        onClickListener = {
                            onChangeCampo(
                                CamposResponsavel.ESTADO_CIVIL_RESPONSAVEL,
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
                        selected = responsavel.situacaoProfissional,
                        onClickListener = {
                            onChangeCampo(
                                CamposResponsavel.SITUACAO_PROFISSIONAL_RESPONSAVEL,
                                it.toString()
                            )
                        },
                    )

                }

                TextFieldSimples(
                    nomeCampo = "Profissão",
                    valorPreenchido = responsavel.profissao,
                    placeholder = "",
                    onChange = {
                        onChangeCampo(
                            CamposResponsavel.PROFISSAO,
                            it
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                ModalEndereco(
                    openBottomSheet = openBottomSheet,
                    pessoa = responsavel,
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