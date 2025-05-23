package br.com.casa_guido.screens.cadastro.formularios.historicoSaudePaciente

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.HistoricoSaude
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CadastroHistoricoSaudePaciente(
    modifier: Modifier = Modifier,
    historicoDoencas: HistoricoSaude,
    numeroTela: Int,
    onChangeCampo: (CamposHistoricoSaude, String) -> Unit
) {

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()


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
                    "$numeroTela. Histórico de Saúde",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações do historico de saude",
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
            .verticalScroll(scrollState)

            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RadioButtonMultOptValores(
            selected = historicoDoencas.recebeBeneficio,
            labelTitulo = "Recebe beneficio",
        ) {
            onChangeCampo(
                CamposHistoricoSaude.RECEBE_BENEFICIO,
                it.toString()
            )
            coroutineScope.launch {
                delay(200)
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }

        AnimatedVisibility(
            visible = historicoDoencas.recebeBeneficio == 0
        ) {
            TextFieldSimples(
                nomeCampo = "Quais beneficios",
                valorPreenchido = historicoDoencas.beneficioDescricao,
                placeholder = "",
                singleLine = false,
                paddingValues = PaddingValues(0.dp, 0.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.BENEFICIO_DESCRICAO,
                    it
                )
            }
        }

        Column {
            TextFieldSimples(
                nomeCampo = "Medicamentos de uso continuo",
                valorPreenchido = historicoDoencas.medicamentosUsoContinuo,
                placeholder = "",
                singleLine = false,
                paddingValues = PaddingValues(0.dp, 0.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.MEDICAMENTOS_USO_CONTINUO,
                    it
                )
            }
        }
    }
}