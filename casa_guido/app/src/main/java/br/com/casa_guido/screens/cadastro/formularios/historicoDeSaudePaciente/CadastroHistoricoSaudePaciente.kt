package br.com.casa_guido.screens.cadastro.formularios.historicoDeSaudePaciente

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CadastroHistoricoSaudePaciente(
    modifier: Modifier = Modifier,
    paciente: Paciente,
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
                    "$numeroTela. Histórico de Saúde do Paciente",
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
            selected = paciente.historicoSaude.recebeBeneficio,
            labelTitulo = "Recebe benefícios",
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
            visible = paciente.historicoSaude.recebeBeneficio == 0
        ) {
            TextFieldSimples(
                nomeCampo = "Quais benefícios",
                valorPreenchido = paciente.historicoSaude.beneficioDescricao,
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
                nomeCampo = "Medicamentos de uso continúo",
                valorPreenchido = paciente.historicoSaude.medicamentosUsoContinuo,
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

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .padding(top = 15.dp)
                    .background(
                        Paragraph,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "UBS para referência",
                    modifier = Modifier.padding(12.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            TextFieldSimples(
                nomeCampo = "Município",
                valorPreenchido = paciente.ubs.municipio,
                placeholder = "",
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.MUNICIPIO_UBS,
                    it
                )
            }

            TextFieldSimples(
                nomeCampo = "Bairro",
                valorPreenchido = paciente.ubs.bairro,
                placeholder = "",
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.BAIRRO_UBS,
                    it
                )
            }
        }

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .padding(top = 15.dp)
                    .background(
                        Paragraph,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "CRAS para referencia",
                    modifier = Modifier.padding(12.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            TextFieldSimples(
                nomeCampo = "Município",
                valorPreenchido = paciente.cras.municipio,
                placeholder = "",
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.MUNICIPIO_CRAS,
                    it
                )
            }

            TextFieldSimples(
                nomeCampo = "Bairro",
                valorPreenchido = paciente.cras.bairro,
                placeholder = "",
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {
                onChangeCampo(
                    CamposHistoricoSaude.BAIRRO_CRAS,
                    it
                )
            }
        }
    }
}