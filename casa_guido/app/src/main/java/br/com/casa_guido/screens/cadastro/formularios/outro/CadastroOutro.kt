package br.com.casa_guido.screens.cadastro.formularios.outro

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
import br.com.casa_guido.screens.Pessoa
import br.com.casa_guido.screens.cadastro.formularios.conjuge.CamposOutro
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.endereco.ModalEndereco
import br.com.casa_guido.screens.components.BotaoPersonalizadoComIcones
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.Alert
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroOutro(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposOutro, String) -> Unit,
    outro: Pessoa,
    onChangeEnderecoOutro: (CamposEndereco, String) -> Unit,
    numeroTela: Int,
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

    LaunchedEffect(outro) {
        color = if (!(outro.endereco.cep.isEmpty()
                    || outro.endereco.numero.isEmpty()
                    || outro.endereco.logradouro.isEmpty()
                    || outro.endereco.bairro.isEmpty()
                    || outro.endereco.referencia.isEmpty()
                    || outro.endereco.localidade.isEmpty()
                    || outro.endereco.uf.isEmpty())
        ) GreenBlack else Alert

        iconeBotao = if (!(outro.endereco.cep.isEmpty()
                    || outro.endereco.numero.isEmpty()
                    || outro.endereco.logradouro.isEmpty()
                    || outro.endereco.bairro.isEmpty()
                    || outro.endereco.referencia.isEmpty()
                    || outro.endereco.localidade.isEmpty()
                    || outro.endereco.uf.isEmpty())
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
                    "$numeroTela. Outro Responsavel",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de outro responsavel",
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
                RadioButtonMultOptValores(
                    opcoesLista = listOf("Sim" to 0),
                    modifier = Modifier.fillMaxWidth(.9f).padding(start = 20.dp),
                    selected = 0,
                    labelTitulo = "Resp Principal",
                ) {

                }

                TextFieldSimples(
                    nomeCampo = "Nome Completo",
                    valorPreenchido = outro.nome,
                    onChange = {
                        onChangeCampo(
                            CamposOutro.NOME_OUTRO,
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
                        nomeCampo = "Cpf",
                        valorPreenchido = outro.cpf,
                        visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                        placeholder = "111.111.111.99",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.CPF_OUTRO,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Rg",
                        valorPreenchido = outro.rg,
                        visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                        placeholder = "11.111.111-9",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.RG_OUTRO,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row {
                    TextFieldSimples(
                        nomeCampo = "Naturalidade",
                        valorPreenchido = outro.naturalidade,
                        visualTransformation = VisualTransformation.None,
                        placeholder = "Brasileiro",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.NATURALIDADE_OUTRO,
                                it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    TextFieldSimples(
                        nomeCampo = "Escolaridade",
                        valorPreenchido = outro.escolaridade,
                        visualTransformation = VisualTransformation.None,
                        placeholder = "8 série",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.ESCOLARIDADE_OUTRO,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }



                Row {
                    DataPicker(
                        showDataPicker = dataPickerNascimentoShow,
                        valorPreenchido = outro.dataNascimento,
                        titulo = "Data de Nascimento",
                        onCancelar = {
                            dataPickerNascimentoShow = false
                        },
                        onChange = {
                            onChangeCampo(
                                CamposOutro.DATA_NASCIMENTO_OUTRO,
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
                        valorPreenchido = outro.telefone,
                        visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                        placeholder = "(48) 99963-9821",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.TELEFONE_OUTRO,
                                it
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                TextFieldSimples(
                    nomeCampo = "Cartão do sus",
                    valorPreenchido = outro.cartaoSus,
                    visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                    placeholder = "567 8901 2345 6789",
                    onChange = {
                        onChangeCampo(
                            CamposOutro.CARTAO_SUS,
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
                        valorPreenchido = outro.salario,
                        visualTransformation = VisualTransformation.None,
                        keyboardType = KeyboardType.Number,
                        placeholder = "1.230",
                        onChange = {
                            onChangeCampo(
                                CamposOutro.SALARIO_OUTRO,
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
                        selected = outro.estadoCivil,
                        onClickListener = {
                            onChangeCampo(
                                CamposOutro.ESTADO_CIVIL_OUTRO,
                                it.toString()
                            )
                        },
                    )

                    RadioButtonMultOptValores(
                        labelTitulo = "Situacao Profissional",
                        opcoesLista = listOf(
                            "Empregado" to 0,
                            "Desempregado" to 1,
                            "Autonomo" to 2,
                            "Pensionista" to 3,
                            "Aposentado" to 4,
                            "Diarista" to 5,
                            "Pensionista" to 6,
                            "Outro" to 7
                        ),
                        selected = outro.situacaoProfissional,
                        onClickListener = {
                            onChangeCampo(
                                CamposOutro.SITUACAO_PROFISSIONAL_OUTRO,
                                it.toString()
                            )
                        },
                    )
                }

                ModalEndereco(
                        openBottomSheet = openBottomSheet,
                        pessoa = outro,
                        onChange = { campo, valor ->
                            onChangeEnderecoOutro(
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