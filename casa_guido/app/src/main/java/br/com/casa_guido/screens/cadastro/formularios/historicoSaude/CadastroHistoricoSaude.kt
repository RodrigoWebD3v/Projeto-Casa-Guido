package br.com.casa_guido.screens.cadastro.formularios.historicoSaude

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.HistoricoSaude
import br.com.casa_guido.screens.components.RadioButtonComLabelWidthIn
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CadastroHistoricoSaude(
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
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Paragraph),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Doencas existentes na familia",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(Modifier.width(10.dp))

            Row(
                Modifier.height(IntrinsicSize.Min)
            ) {
                Column {
                    RadioButtonComLabelWidthIn(
                        label = "Alcoolismo",
                        selected = 1 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                1.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Deficiencia Intelectual",
                        selected = 2 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                2.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Deficiencia Fisica",
                        selected = 3 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                3.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Deficiencia Auditiva",
                        selected = 4 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                4.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Deficiencia Visual",
                        selected = 5 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                5.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Cardiopatia",
                        selected = 6 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                6.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Asma",
                        selected = 7 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                7.toString()
                            )
                        }
                    )


                    RadioButtonComLabelWidthIn(
                        label = "Bronquite",
                        selected = 8 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                8.toString()
                            )
                        }
                    )
                }

                VerticalDivider(
                    thickness = 1.dp,
                    color = GreenBlack
                )

                Column(
                    Modifier.padding(start = 10.dp)
                ) {
                    RadioButtonComLabelWidthIn(
                        label = "Hipert. arterial",
                        selected = 9 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                9.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Toxoplasmose",
                        selected = 10 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                10.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Drogadito",
                        selected = 11 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                11.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Epilepsia",
                        selected = 12 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                12.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Desnutricao",
                        selected = 13 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                13.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Diabete",
                        selected = 14 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                14.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Cancer",
                        selected = 15 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                15.toString()
                            )
                        }
                    )


                    RadioButtonComLabelWidthIn(
                        label = "Outros",
                        selected = 16 in historicoDoencas.doencasFamilia,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.DOENCA_FAMILIA,
                                16.toString()
                            )
                        }
                    )
                }
            }

        }

        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Paragraph),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Em caso de doenca, procura",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(Modifier.width(10.dp))

            Row(
                Modifier.height(IntrinsicSize.Min)
            ) {
                Column {
                    RadioButtonComLabelWidthIn(
                        label = "Hospital",
                        selected = 1 in historicoDoencas.localProcuraAtendimento,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO,
                                1.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Unidade de Saúde",
                        selected = 2 in historicoDoencas.localProcuraAtendimento,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO,
                                2.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Farmacia",
                        selected = 3 in historicoDoencas.localProcuraAtendimento,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO,
                                3.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Pastoral",
                        selected = 4 in historicoDoencas.localProcuraAtendimento,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO,
                                4.toString()
                            )
                        }
                    )

                    RadioButtonComLabelWidthIn(
                        label = "Outro",
                        selected = 5 in historicoDoencas.localProcuraAtendimento,
                        onClickListener = {
                            onChangeCampo(
                                CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO,
                                5.toString()
                            )
                        }
                    )
                }
            }

        }


        RadioButtonMultOptValores(
            selected = historicoDoencas.recebeBeneficio ?: 0,
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