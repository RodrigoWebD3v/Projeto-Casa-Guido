package br.com.casa_guido.screens.cadastro.formularios.socioEconomico

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.components.DropDownMenu
import br.com.casa_guido.screens.components.DropDownMenuItem
import br.com.casa_guido.screens.components.Escolaridade
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.GreenBlack
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
        horizontalAlignment = Alignment.CenterHorizontally
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
            visible = paciente.bpc == 0
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
            nomeCampo = "Instituicao de ensino",
            valorPreenchido = paciente.escolaNome,
            placeholder = "E.E.B Ignacio Stakowski",
            onChange = {
                onChangeCampo(
                    CamposSocioEconomico.NOME_ESCOLA,
                    it
                )
            }
        )

        Escolaridade(
            pessoa = paciente.pessoa,
            onChangeEscolaridade = { valor1, valor2 ->
                onChangeCampo(
                    CamposSocioEconomico.ESCOLARIDADE,
                    valor1
                )
                onChangeCampo(
                    CamposSocioEconomico.SERIE,
                    valor2
                )
            }
        )


        RadioButtonMultOptValores(
            opcoesLista = listOf("Publica" to 0, "Privada" to 1),
            modifier = Modifier.fillMaxWidth(.9f),
            selected = paciente.tipoEscola,
            labelTitulo = "Escola"
        ) {
            onChangeCampo(
                CamposSocioEconomico.PUBLICA_PRIVADA,
                it.toString()
            )
        }

    }
}


