package br.com.casa_guido.screens.cadastro.formularios.socioEconomico

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.shared.RadioButtonComLabel
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun SocioEconomico(
    modifier: Modifier = Modifier, paciente: Paciente,
    onChangeCampo: (CamposSocioEconomico, String) -> Unit,
    onCollapse: () -> Unit,
) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Paragraph)
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
                        "3. Sócio econômico",
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
                    valorPreenchido = paciente.socioEconomico.remuneracao,
                    onChange = {
                        onChangeCampo(
                            CamposSocioEconomico.REMUNERACAO,
                            it
                        )
                    }
                )

                var selecionado by remember {
                    mutableIntStateOf(paciente.socioEconomico.bpc)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier
                            .size(50.dp, 35.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Paragraph),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "BPC",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = BackgroundColor,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RadioButtonComLabel(
                            label = "Sim",
                            selected = selecionado == 1,
                            onClickListener = {
                                selecionado = 1

                                onChangeCampo(
                                    CamposSocioEconomico.REMUNERACAO_OPT,
                                    selecionado.toString()
                                )
                            }
                        )

                        RadioButtonComLabel(
                            label = "Não",
                            selected = selecionado == 0,
                            onClickListener = {
                                selecionado = 0

                                onChangeCampo(
                                    CamposSocioEconomico.REMUNERACAO_OPT,
                                    selecionado.toString()
                                )
                            }
                        )

                        RadioButtonComLabel(
                            label = "Apto a receber",
                            selected = selecionado == 2,
                            onClickListener = {
                                selecionado = 2

                                onChangeCampo(
                                    CamposSocioEconomico.REMUNERACAO_OPT,
                                    selecionado.toString()
                                )
                            }
                        )

                    }
                }

                if (selecionado == 1) {
                    TextFieldSimples(
                        nomeCampo = "Valor",
                        valorPreenchido = paciente.socioEconomico.valorBpc,
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
                    valorPreenchido = paciente.socioEconomico.escolaNome,
                    onChange = {
                        onChangeCampo(
                            CamposSocioEconomico.NOME_ESCOLA,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Ano",
                    valorPreenchido = paciente.socioEconomico.escolaAno,
                    onChange = {
                        onChangeCampo(
                            CamposSocioEconomico.ESCOLA_ANO,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Tam. Roupa",
                    valorPreenchido = paciente.socioEconomico.tamRoupa.toString(),
                    onChange = {
                        onChangeCampo(
                            CamposSocioEconomico.TAMANHO_ROUPA,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Tam. Calçado",
                    valorPreenchido = paciente.socioEconomico.tamCalcado.toString(),
                    onChange = {
                        onChangeCampo(
                            CamposSocioEconomico.TAMANHO_CALCADO,
                            it
                        )
                    }
                )
            }
        }

