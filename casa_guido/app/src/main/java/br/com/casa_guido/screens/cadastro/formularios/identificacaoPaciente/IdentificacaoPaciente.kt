package br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.customizacao.VisualTransformationCustom
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IdentificacaoPaciente(
    modifier: Modifier = Modifier,
    paciente: Paciente,
    selectedItem: Boolean,
    onChangeCampo: (CamposIdentificacao, String) -> Unit,
    onCollapse: () -> Unit,
) {

    Card(
        modifier
            .fillMaxWidth(.95f)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)
            ).clickable {
                onCollapse()
            }

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Paragraph)
                .animateContentSize()
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
                        "1. Identificação do Paciente",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    )
                    Text(
                        "Informações pessoais e de contato do paciente",
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
            visible = (selectedItem)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Main)
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                TextFieldSimples(
                    nomeCampo = "NOME COMPLETO",
                    valorPreenchido = paciente.nome,
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.NOME,
                            it
                        )
                    }
                )

                var  dataPickerNascimentoShow by remember {
                    mutableStateOf(false)
                }

                DataPicker(
                    showDataPicker = dataPickerNascimentoShow,
                    valorPreenchido = paciente.dataNascimento,
                    titulo = "Data de Nascimento",
                    onCancelar = {
                        dataPickerNascimentoShow = false
                    },
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.DATA_NASCIMENTO,
                            it
                        )
                    },
                    onClick = {
                        dataPickerNascimentoShow = true
                    },
                )

                TextFieldSimples(
                    nomeCampo = "MÃE",
                    valorPreenchido = paciente.nomeMae,
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.NOME_MAE,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "PAI",
                    valorPreenchido = paciente.nomePai,
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.NOME_PAI,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "OUTRO",
                    valorPreenchido = paciente.nomeResponsavel,
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.NOME_RESPONSAVEL,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "CPF",
                    valorPreenchido = paciente.cpf,
                    visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.CPF,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "RG",
                    valorPreenchido = paciente.rg,
                    visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.RG,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "CARTÃO SUS",
                    valorPreenchido = paciente.cartaoSus,
                    visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.CARTAO_SUS,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "CELULAR",
                    valorPreenchido = paciente.telefone,
                    visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                    onChange = {
                        onChangeCampo(
                            CamposIdentificacao.TELEFONE,
                            it
                        )
                    }
                )
            }
        }
    }

}