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


        var escolaridadeState by remember {
            mutableStateOf(
                DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0")

            )
        }

        var seriestate by remember {
            mutableStateOf(
                "0"
            )
        }

        val escolaridade = listOf(
            DropDownMenuItem(nome = "Fundamental", icone = Icons.Filled.Home, id = "1"),
            DropDownMenuItem(nome = "Médio", icone = Icons.Filled.Settings, id = "2"),
            DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0")
        )

        val serieFundamental = listOf(
            DropDownMenuItem(nome = "1", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "2", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "3", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "4", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "5", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "6", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "7", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "8", icone = Icons.Filled.Check),
            DropDownMenuItem(nome = "9", icone = Icons.Filled.Check),
        )

        val serieMedio = listOf(
            DropDownMenuItem(nome = "1", icone = Icons.Filled.Home),
            DropDownMenuItem(nome = "2", icone = Icons.Filled.Settings),
            DropDownMenuItem(nome = "3", icone = Icons.Filled.Settings),
        )

        val opcoesDefault = listOf(
            DropDownMenuItem(nome = "Selecione", icone = Icons.Filled.Settings, id = "0")
        )

        Row (
            Modifier.fillMaxWidth(.9f)
        ){
            Text(
                "Escolaridade",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }


        DropDownMenu(
            opcoes = escolaridade,
            modifier = Modifier.fillMaxWidth(.9f)
        ) {
            escolaridadeState = it
        }


        Row (
            Modifier.fillMaxWidth(.9f)
        ){
            Text(
                "Série",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }

        DropDownMenu(
            modifier = Modifier.fillMaxWidth(.9f),
            opcoes = when (escolaridadeState.id) {
                "1" -> serieFundamental
                "2" -> serieMedio
                else -> opcoesDefault
            }
        ) {
            seriestate = it.id
        }


        RadioButtonMultOptValores(
            opcoesLista = listOf("Publica" to 0, "Privada" to 1),
            modifier = Modifier.fillMaxWidth(.9f),
            selected = 0,
            labelTitulo = "Escola"
        ) { }

    }
}


