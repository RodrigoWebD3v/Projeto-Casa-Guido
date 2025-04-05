package br.com.casa_guido.screens.cadastro.components.CamposPaciente

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CamposPaciente(
    modifier: Modifier = Modifier,
    paciente: Paciente,
    onChange: (CamposPacienteUiState) -> Unit
) {

    val viewModel = koinViewModel<CamposPacienteViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (paciente != Paciente()) {
        viewModel.setPaciente(paciente)
    }


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(5.dp),
            )

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(10.dp)
        ) {
            Text(
                "1. Identificação do Paciente",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
            Text(
                "Informações pessoais e de contato do paciente",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Main)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {

            TextFieldSimples(
                nomeCampo = "Nome completo do paciente",
                valorPreenchido = state.nomePaciente,
                onChange = {
                    viewModel.onNomePacienteChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    "Data de nascimento", style = TextStyle(
                        fontSize = 14.sp,
                        color = GreenBlack,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Paragraph,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .height(50.dp)
                        .clickable {
                            viewModel.onDataPickerShowChange(
                                true
                            )
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.dataNascimento,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Ícone de calendário",
                        tint = BackgroundColor,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(20.dp)
                    )
                }
            }

            DataPicker(
                showDataPicker = state.dataPickerShow,
                onChange = {
                    viewModel.onDataNascimentoChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "Nome da Mãe",
                valorPreenchido = state.nomeMae,
                onChange = {
                    viewModel.onNomeMaeChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "Nome do Pai",
                valorPreenchido = state.nomePai,
                onChange = {
                    viewModel.onNomePaiChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "Nome do Responsável",
                valorPreenchido = state.nomeResponsavel,
                onChange = {
                    viewModel.onNomeResponsavelChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "CPF",
                valorPreenchido = state.cpf,
                onChange = {
                    viewModel.onCpfChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "RG",
                valorPreenchido = state.rg,
                onChange = {
                    viewModel.onRgChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "Cartão SUS",
                valorPreenchido = state.cartaoSus,
                onChange = {
                    viewModel.onCartaoSusChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )

            TextFieldSimples(
                nomeCampo = "Celular",
                valorPreenchido = state.celular,
                onChange = {
                    viewModel.onCelularChange(it)
                    onChange(
                        state.copy()
                    )
                }
            )
        }
    }
}

