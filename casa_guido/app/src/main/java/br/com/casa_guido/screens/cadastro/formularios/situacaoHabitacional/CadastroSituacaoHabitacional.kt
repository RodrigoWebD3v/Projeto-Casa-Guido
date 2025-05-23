package br.com.casa_guido.screens.cadastro.formularios.situacaoHabitacional

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.SituacaoHabitacional
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.RadioButtonMultValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun CadastroSituacaoHabitacional(
    modifier: Modifier = Modifier,
    numeroTela: Int,
    onChangeCampo: (CamposSituacaoHabitacional, String) -> Unit,
    situacaoHabitacional: SituacaoHabitacional,
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
                    "$numeroTela. Situacao Habitacional", style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações habitacionais", style = TextStyle(
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
        Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        RadioButtonMultOptValores(
            selected = situacaoHabitacional.comoAdquiriuCasa,
            labelTitulo = "Como adquiriu a casa",
            opcoesLista = listOf(
                "Doacao" to 0,
                "Ocupacao" to 1,
                "Comprada" to 2,
                "Alugada" to 3,
                "Cedida" to 4,
            )
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.COMO_ADQUIRIU_CASA, it.toString()
            )
        }

        RadioButtonMultOptValores(
            selected = situacaoHabitacional.area, labelTitulo = "Área", opcoesLista = listOf(
                "Pública" to 0,
                "Privada" to 1,
            )
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.AREA, it.toString()
            )
        }

        TextFieldSimples(
            nomeCampo = "Numero de Comodos",
            valorPreenchido = (situacaoHabitacional.numeroComodos ?: 1).toString(),
            placeholder = "",
            paddingValues = PaddingValues(0.dp)
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.NUMERO_COMODOS, it
            )
        }

        RadioButtonMultOptValores(
            selected = situacaoHabitacional.material,
            labelTitulo = "Caracteristicas",
            opcoesLista = listOf(
                "Alvenaria" to 0,
                "Madeira" to 1,
                "Mista" to 2,
            )
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.MATERIAL, it.toString()
            )
        }

        RadioButtonMultValores(
            opcoesLista = listOf("Terreno", "Carro", "Outro"),
            labelTitulo = "Bens/Imovel",
            opcoesSelecionadas = situacaoHabitacional.bens,
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.BENS,
                it.toString()
            )
        }

        RadioButtonMultOptValores(
            opcoesLista = listOf("Carro" to 0, "Moto" to 1, "Outro" to 2),
            labelTitulo = "Meio de transporte + usado",
            selected = situacaoHabitacional.meioDeTransporte,
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.MEIO_TRANSPORTE,
                it.toString()
            )
        }

        RadioButtonMultOptValores(
            opcoesLista = listOf("TV" to 0, "Radio" to 1, "Internet" to 2, "Outro" to 3),
            labelTitulo = "Meio de comunicacao + usado",
            selected = situacaoHabitacional.meioDeComunicao,
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.MEIO_COMUNICACAO,
                it.toString()
            )
        }

        RadioButtonMultOptValores(
            labelTitulo = "Possui banheiro",
            selected = situacaoHabitacional.possuiBanheiros,
        ) {
            onChangeCampo(
                CamposSituacaoHabitacional.POSSUI_BANHEIRO,
                it.toString()
            )
        }

        AnimatedVisibility(
            situacaoHabitacional.possuiBanheiros == 0
        ) {
            RadioButtonMultOptValores(
                labelTitulo = "Dentro de casa",
                selected = situacaoHabitacional.dentroDeCasa,
            ) {
                onChangeCampo(
                    CamposSituacaoHabitacional.DENTRO_DE_CASA,
                    it.toString()
                )
            }
        }

//        TextFieldSimples(
//            nomeCampo = "Valor",
//            placeholder = "",
//            valorPreenchido = (situacaoHabitacional.valorTotal ?: 0).toString(),
//            paddingValues = PaddingValues(0.dp)
//        ) {
//            onChangeCampo(
//                CamposSituacaoHabitacional.VALOR_TOTAL,
//                it
//            )
//        }
    }
}