package br.com.casa_guido.screens.cadastro.formularios.endereco

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun Endereco(
    modifier: Modifier = Modifier, paciente: Paciente,
    selectedItem: Boolean,
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
) {
    Card(
        modifier
            .fillMaxWidth(.95f)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)
            )


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
                Icon(
                    imageVector = if (selectedItem) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Adicionar",
                    tint = GreenBlack,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(20.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = (selectedItem),
            enter = expandVertically(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = shrinkVertically() + fadeOut(),

            ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Main)
                    .animateContentSize()
                    .padding(vertical = 10.dp),
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
    }

}