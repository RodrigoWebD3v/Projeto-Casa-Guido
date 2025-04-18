package br.com.casa_guido.screens.cadastro.formularios.endereco

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.Pessoa
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun Endereco(
    modifier: Modifier = Modifier, pessoa: Pessoa,
    onChangeCampo: (CamposEndereco, String) -> Unit,
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
                    "Endereço", style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de endereço do paciente", style = TextStyle(
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
        Row {
            TextFieldSimples(
                nomeCampo = "Cep",
                valorPreenchido = pessoa.endereco.cep,
                placeholder = "",
                keyboardType = KeyboardType.Number,
                onChange = {
                    onChangeCampo(
                        CamposEndereco.CEP, it
                    )
                },
                modifier = Modifier.fillMaxWidth(.6f)
            )
            TextFieldSimples(
                nomeCampo = "Número",
                keyboardType = KeyboardType.Number,
                valorPreenchido = pessoa.endereco.numero,
                placeholder = "",
                onChange = {
                    onChangeCampo(
                        CamposEndereco.NUMERO, it
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }

        TextFieldSimples(
            nomeCampo = "Logradouro",
            valorPreenchido = pessoa.endereco.logradouro,
            placeholder = "",
            onChange = {
                onChangeCampo(
                    CamposEndereco.LOGRADOURO, it
                )
            })

        TextFieldSimples(
            nomeCampo = "Bairro",
            valorPreenchido = pessoa.endereco.bairro,
            placeholder = "",
            onChange = {
                onChangeCampo(
                    CamposEndereco.BAIRRO, it
                )
            })

        TextFieldSimples(
            nomeCampo = "Próximo",
            valorPreenchido = pessoa.endereco.referencia,
            placeholder = "",
            onChange = {
                onChangeCampo(
                    CamposEndereco.REFERENCIA, it
                )
            })

        Row {
            TextFieldSimples(
                nomeCampo = "Localidade",
                valorPreenchido = pessoa.endereco.localidade,
                placeholder = "",
                onChange = {
                    onChangeCampo(
                        CamposEndereco.LOCALIDADE, it
                    )
                },
                modifier = Modifier.fillMaxWidth(.75f)
            )

            TextFieldSimples(
                nomeCampo = "UF",
                valorPreenchido = pessoa.endereco.uf,
                placeholder = "",
                onChange = {
                    onChangeCampo(
                        CamposEndereco.UF, it
                    )
                },
                modifier = Modifier.weight(1f)
                )
        }


    }
}
