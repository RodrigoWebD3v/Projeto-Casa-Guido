package br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IdentificacaoPaciente(
    modifier: Modifier = Modifier,
    paciente: Paciente,
    onChangeCampo: (CamposIdentificacao, String) -> Unit,
    onCollapse: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Paragraph)
            .animateContentSize()
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
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Main)
            .padding(vertical = 10.dp)
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextFieldSimples(
            nomeCampo = "Nome Completo",
            valorPreenchido = paciente.nome,
            onChange = {
                onChangeCampo(
                    CamposIdentificacao.NOME,
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
            nomeCampo = "Mãe",
            valorPreenchido = paciente.nomeMae,
            placeholder = "Maria Aparecida",
            onChange = {
                onChangeCampo(
                    CamposIdentificacao.NOME_MAE,
                    it
                )
            }
        )

        TextFieldSimples(
            nomeCampo = "Pai",
            valorPreenchido = paciente.nomePai,
            placeholder = "Valdir Jorge",
            onChange = {
                onChangeCampo(
                    CamposIdentificacao.NOME_PAI,
                    it
                )
            }
        )

        TextFieldSimples(
            nomeCampo = "Outro",
            valorPreenchido = paciente.nomeResponsavel,
            placeholder = "Maria Aparecida",
            onChange = {
                onChangeCampo(
                    CamposIdentificacao.NOME_RESPONSAVEL,
                    it
                )
            }
        )



        Row {
            TextFieldSimples(
                nomeCampo = "Cpf",
                valorPreenchido = paciente.cpf,
                visualTransformation = VisualTransformationCustom.CpfVisualTransformation(),
                placeholder = "111.111.111.99",
                onChange = {
                    onChangeCampo(
                        CamposIdentificacao.CPF,
                        it
                    )
                },
                modifier = Modifier.fillMaxWidth(.5f)
            )

            TextFieldSimples(
                nomeCampo = "Rg",
                valorPreenchido = paciente.rg,
                visualTransformation = VisualTransformationCustom.RgVisualTransformation(),
                placeholder = "11.111.111-9",
                onChange = {
                    onChangeCampo(
                        CamposIdentificacao.RG,
                        it
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }


        Row {
            TextFieldSimples(
                nomeCampo = "Cartão do sus",
                valorPreenchido = paciente.cartaoSus,
                visualTransformation = VisualTransformationCustom.CartSusVisualTransformation(),
                placeholder = "567 8901 2345 6789",
                onChange = {
                    onChangeCampo(
                        CamposIdentificacao.CARTAO_SUS,
                        it
                    )
                },
                modifier = Modifier.fillMaxWidth(.5f),
                paddingValues = PaddingValues(start = 20.dp, end = 0.dp),
            )

            TextFieldSimples(
                nomeCampo = "Celular",
                valorPreenchido = paciente.telefone,

                visualTransformation = VisualTransformationCustom.PhoneVisualTransformation(),
                placeholder = "(48) 99963-9821",
                onChange = {
                    onChangeCampo(
                        CamposIdentificacao.TELEFONE,
                        it
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }

    }
}
