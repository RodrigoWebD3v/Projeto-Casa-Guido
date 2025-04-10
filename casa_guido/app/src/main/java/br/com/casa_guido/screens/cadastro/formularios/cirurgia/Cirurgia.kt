package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import br.com.casa_guido.screens.Cirurgia
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Cirurgia(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposEndereco, String) -> Unit,
    onCollapse: () -> Unit,
) {

    val viewModel = koinViewModel<CirurgiaViewModel>()
    val state by viewModel.uiState.collectAsState()

    var cirurgiaEdicao by remember {
        mutableStateOf(Cirurgia())
    }


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
                    "4. Cirurgias",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações de cirurgias do paciente",
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
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldSimples(
                        nomeCampo = "Nome da cirurgia",
                        valorPreenchido = cirurgiaEdicao.nome,
                        placeholder = "Nefrectomia",
                        onChange = {
                            cirurgiaEdicao = cirurgiaEdicao.copy(
                                nome = it
                            )
                        },
                        modifier = Modifier.fillMaxWidth(.6f),
                        paddingValues = PaddingValues(start = 20.dp, end = 0.dp)
                    )


                    DataPicker(
                        showDataPicker = state.toggleDataPickerCirurgia,
                        valorPreenchido = cirurgiaEdicao.data,
                        titulo = "Data da cirurgia",
                        onCancelar = {
                            viewModel.toggleDatePickerCirurgia()
                        },
                        onChange = {
                            cirurgiaEdicao = cirurgiaEdicao.copy(
                                data = it
                            )
                        },
                        onClick = {
                            viewModel.toggleDatePickerCirurgia()
                        },
                    )
                }

            }

            Button(
                onClick = {
                    if (cirurgiaEdicao.nome.isNotEmpty()) {
                        viewModel.addCirurgia(cirurgiaEdicao)
                        cirurgiaEdicao = Cirurgia(
                            data = Utils.formatData(LocalDate.now())!!
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BackgroundColor),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Adicionar cirurgia",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = br.com.casa_guido.ui.theme.Button,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }


            Column {
                state.listaCirurgias.forEachIndexed {
                    index, item ->
                    ItemCirurgia(
                        nomeCirurgia = item.nome,
                        data = item.data
                    ) {
                        viewModel.RemoveIndex(index)
                    }
                }
            }
        }
    }

}




