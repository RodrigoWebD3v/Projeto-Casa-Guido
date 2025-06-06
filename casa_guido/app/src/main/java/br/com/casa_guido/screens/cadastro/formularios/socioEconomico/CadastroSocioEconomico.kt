package br.com.casa_guido.screens.cadastro.formularios.socioEconomico

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.screens.components.escolaridade.Escolaridade
import br.com.casa_guido.screens.components.RadioButtonMultOptValores
import br.com.casa_guido.screens.components.TextFieldSimples
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
            somenteNumero = true,
            valorPreenchido = paciente.remuneracao,
            placeholder = "1.500",
            onChange = {
                onChangeCampo(
                    CamposSocioEconomico.REMUNERACAO,
                    it
                )
            }
        )

        Column {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            RadioButtonMultOptValores(
                opcoesLista = listOf("Não" to 0, "Sim" to 1),
                labelTitulo = "BPC",
                selected = paciente.bpc
            ) {
                onChangeCampo(
                    CamposSocioEconomico.BPC_OPCIONAL,
                    it.toString()
                )
            }
        }
        if (paciente.bpc == 0) {
            AnimatedVisibility(
                visible = paciente.bpc == 0
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButtonMultOptValores(
                        opcoesLista = listOf("Não" to 0, "Sim" to 1),
                        labelTitulo = "Apto a receber",
                        selected = paciente.aptoReceberBpc
                    ) {
                        onChangeCampo(
                            CamposSocioEconomico.APTO_RECEBER_BPC,
                            it.toString()
                        )
                    }
                }
            }
        } else {
            AnimatedVisibility(
                visible = paciente.bpc == 1,
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

        Log.i(
            "Cadastro socio economico 2",
            "Inicializando com escolaridade: ${paciente.pessoa.escolaridade} e série: ${paciente.pessoa.serie}"
        )

        Escolaridade(
            pessoa = paciente.pessoa,
            modifier = Modifier.fillMaxWidth(.9f),
            onChangeEscolaridade = { valor1, valor2 ->
                onChangeCampo(
                    CamposSocioEconomico.ESCOLARIDADE,
                    valor1.toString()
                )

                onChangeCampo(
                    CamposSocioEconomico.SERIE,
                    valor2.toString()
                )
            }
        )


        RadioButtonMultOptValores(
            opcoesLista = listOf("Publica" to 0, "Privada" to 1),
            modifier = Modifier.fillMaxWidth(.9f),
            selected = paciente.tipoEscola,
            labelTitulo = "Escola"
        ) {
            onChangeCampo(
                CamposSocioEconomico.PUBLICA_PRIVADA,
                it.toString()
            )
        }

    }
}


