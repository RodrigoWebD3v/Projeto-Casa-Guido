package br.com.casa_guido.screens.cadastro.formularios.historicoDeSaudeFamiliar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.HistoricoSaude
import br.com.casa_guido.screens.cadastro.formularios.historicoDeSaudePaciente.CamposHistoricoSaude
import br.com.casa_guido.screens.components.RadioButtonComLabelWidthIn
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun CadastroHistoricoDeSaudeFamiliar(
    modifier: Modifier = Modifier,
    numeroTela: Int,
    historicoDoencas: HistoricoSaude,
    onChangeCampo: (CamposHistoricoSaude, String) -> Unit
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
                    "$numeroTela. Histórico de Saúde Familiar",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações do histórico de saúde",
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
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
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
                text = "Doenças existentes na família",
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
                    label = "Deficiência Intelectual",
                    selected = 2 in historicoDoencas.doencasFamilia,
                    onClickListener = {
                        onChangeCampo(
                            CamposHistoricoSaude.DOENCA_FAMILIA,
                            2.toString()
                        )
                    }
                )

                RadioButtonComLabelWidthIn(
                    label = "Deficiência Física",
                    selected = 3 in historicoDoencas.doencasFamilia,
                    onClickListener = {
                        onChangeCampo(
                            CamposHistoricoSaude.DOENCA_FAMILIA,
                            3.toString()
                        )
                    }
                )

                RadioButtonComLabelWidthIn(
                    label = "Deficiência Auditiva",
                    selected = 4 in historicoDoencas.doencasFamilia,
                    onClickListener = {
                        onChangeCampo(
                            CamposHistoricoSaude.DOENCA_FAMILIA,
                            4.toString()
                        )
                    }
                )

                RadioButtonComLabelWidthIn(
                    label = "Deficiência Visual",
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
                    label = "Desnutrição",
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
                    label = "Câncer",
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
}
