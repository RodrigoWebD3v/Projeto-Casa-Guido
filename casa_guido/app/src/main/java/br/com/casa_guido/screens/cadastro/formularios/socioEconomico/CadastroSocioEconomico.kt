package br.com.casa_guido.screens.cadastro.formularios.socioEconomico

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun CadastroSocioEconomico(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposSocioEconomico, String) -> Unit,
    paciente: Paciente,
    numeroTela: Int
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
                    "$numeroTela. Sócio econômico",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações sócio econômicas do paciente",
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
        TextFieldSimples(
            nomeCampo = "Remuneração",
            valorPreenchido = paciente.remuneracao,
            placeholder = "1.500",
            onChange = {
                onChangeCampo(
                    CamposSocioEconomico.REMUNERACAO,
                    it
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            RadioButtonMultOptValores(
                opcoesLista = listOf("Sim" to 0, "Não" to 1, "Apto a receber" to 2),
                labelTitulo = "BPC",
                selected = paciente.bpc
            ) {
                onChangeCampo(
                    CamposSocioEconomico.REMUNERACAO_OPT,
                    it.toString()
                )
            }
        }

        AnimatedVisibility(
            visible = paciente.bpc == 1
        ) {
            TextFieldSimples(
                nomeCampo = "Valor",
                valorPreenchido = paciente.valorBpc,
                placeholder = "180",
                onChange = {
                    onChangeCampo(
                        CamposSocioEconomico.VALOR_BPC,
                        it
                    )
                }
            )
        }

        TextFieldSimples(
            nomeCampo = "Escola",
            valorPreenchido = paciente.escolaNome,
            placeholder = "E.E.B Ignacio Stakowski",
            onChange = {
                onChangeCampo(
                    CamposSocioEconomico.NOME_ESCOLA,
                    it
                )
            }
        )

        Row {
            TextFieldSimples(
                nomeCampo = "Ano",
                valorPreenchido = paciente.pessoa.escolaridade,
                placeholder = "3",
                onChange = {
                    onChangeCampo(
                        CamposSocioEconomico.ESCOLA_ANO,
                        it
                    )
                },
                modifier = Modifier.weight(1f)
            )

            TextFieldSimples(
                nomeCampo = "Tam. Roupa",
                valorPreenchido = paciente.tamRoupa,
                placeholder = "44",
                onChange = {
                    onChangeCampo(
                        CamposSocioEconomico.TAMANHO_ROUPA,
                        it
                    )
                },
                modifier = Modifier.weight(1f)
            )

            TextFieldSimples(
                nomeCampo = "Tam. Calçado",
                placeholder = "32",
                valorPreenchido = paciente.tamCalcado,
                keyboardType = KeyboardType.Number,
                somenteNumero = true,
                onChange = {

                    onChangeCampo(
                        CamposSocioEconomico.TAMANHO_CALCADO,
                        it
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

