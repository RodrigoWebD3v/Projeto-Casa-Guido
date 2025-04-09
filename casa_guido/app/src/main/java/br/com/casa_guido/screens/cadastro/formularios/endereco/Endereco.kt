package br.com.casa_guido.screens.cadastro.formularios.endereco

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun Endereco(
    modifier: Modifier = Modifier, paciente: Paciente,
    onChangeCampo: (CamposEndereco, String) -> Unit,
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
                        "2. Endereço",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    )
                    Text(
                        "Informações de endereço do paciente",
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
                    nomeCampo = "Cep",
                    valorPreenchido = paciente.endereco.cep,
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.CEP,
                            it
                        )
                    }
                )
                TextFieldSimples(
                    nomeCampo = "Logradouro",
                    valorPreenchido = paciente.endereco.logradouro,
                    onChange = {
                       onChangeCampo(
                            CamposEndereco.LOGRADOURO,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Bairro",
                    valorPreenchido = paciente.endereco.bairro,
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.LOGRADOURO,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Complemento",
                    valorPreenchido = paciente.endereco.complemento,
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.COMPLEMENTO,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "Localidade",
                    valorPreenchido = paciente.endereco.localidade,
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.LOCALIDADE,
                            it
                        )
                    }
                )

                TextFieldSimples(
                    nomeCampo = "UF",
                    valorPreenchido = paciente.endereco.uf,
                    onChange = {
                        onChangeCampo(
                            CamposEndereco.UF,
                            it
                        )
                    }
                )
        }
    }
