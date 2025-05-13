package br.com.casa_guido.screens.cadastro.formularios.compFamiliar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import br.com.casa_guido.screens.ComposicaoFamiliar
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.RadioButtonComLabel
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroComposicaoFamiliar(
    modifier: Modifier = Modifier,
    onChangeCampo: (CamposCompFamiliar, ComposicaoFamiliar) -> Unit,
    listaComposicao: List<ComposicaoFamiliar>,
    numeroTela: Int
) {
    val viewModel = koinViewModel<ComposicaoFamiliarViewModel>()
    val state by viewModel.uiState.collectAsState()

    var dataPickerNascimentoShow by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Paragraph)
            .animateContentSize()

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
                    "$numeroTela. Composicão Familiar",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Informações da composicão familiar",
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
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        TextFieldSimples(
            nomeCampo = "Nome",
            valorPreenchido = state.composicaoFamiliarEdicao.nome,
            placeholder = "Digite o nome",
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.onChangeComFamiliaEdicao(
                state.composicaoFamiliarEdicao.copy(
                    nome = it
                )
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            TextFieldSimples(
                nomeCampo = "Parentesco",
                valorPreenchido = state.composicaoFamiliarEdicao.parentesco,
                placeholder = "",
                modifier = Modifier.weight(1f)
            ) {
                viewModel.onChangeComFamiliaEdicao(
                    state.composicaoFamiliarEdicao.copy(
                        parentesco = it
                    )
                )
            }

            DataPicker(
                showDataPicker = dataPickerNascimentoShow,
                valorPreenchido = state.composicaoFamiliarEdicao.dataNascimento,
                titulo = "Data de Nascimento",
                onCancelar = { dataPickerNascimentoShow = false },
                onChange = {
                    viewModel.onChangeComFamiliaEdicao(
                        state.composicaoFamiliarEdicao.copy(
                            dataNascimento = it
                        )
                    )
                },
                onClick = { dataPickerNascimentoShow = true },
                modifier = Modifier.weight(1f)
            )
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
                    .size(80.dp, 35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Paragraph),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Estuda",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                RadioButtonComLabel(
                    label = "Sim",
                    selected = state.composicaoFamiliarEdicao.estudaOpcional == 0,
                    onClickListener = {
                        viewModel.onChangeComFamiliaEdicao(
                            state.composicaoFamiliarEdicao.copy(
                                estudaOpcional = 0
                            )
                        )
                    }
                )

                RadioButtonComLabel(
                    label = "Não",
                    selected = state.composicaoFamiliarEdicao.estudaOpcional == 1,
                    onClickListener = {
                        viewModel.onChangeComFamiliaEdicao(
                            state.composicaoFamiliarEdicao.copy(
                                estudaOpcional = 1
                            )
                        )
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = state.composicaoFamiliarEdicao.estudaOpcional == 0
        ) {
            TextFieldSimples(
                nomeCampo = "Ano",
                valorPreenchido = state.composicaoFamiliarEdicao.serie,
                placeholder = "",
                modifier = Modifier.weight(1f),
                onChange = {
                    viewModel.onChangeComFamiliaEdicao(
                        state.composicaoFamiliarEdicao.copy(
                            serie = it
                        )
                    )
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .size(85.dp, 35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Paragraph),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Trabalha",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                RadioButtonComLabel(
                    label = "Sim",
                    selected = state.composicaoFamiliarEdicao.trabalhaOpcional == 0,
                    onClickListener = {
                        viewModel.onChangeComFamiliaEdicao(
                            state.composicaoFamiliarEdicao.copy(
                                trabalhaOpcional = 0
                            )
                        )
                    }
                )

                RadioButtonComLabel(
                    label = "Não",
                    selected = state.composicaoFamiliarEdicao.trabalhaOpcional == 1,
                    onClickListener = {
                        viewModel.onChangeComFamiliaEdicao(
                            state.composicaoFamiliarEdicao.copy(
                                trabalhaOpcional = 1
                            )
                        )
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = state.composicaoFamiliarEdicao.trabalhaOpcional == 0
        ) {
            TextFieldSimples(
                nomeCampo = "Renda",
                valorPreenchido = state.composicaoFamiliarEdicao.renda,
                placeholder = "",
                modifier = Modifier.weight(1f),
                onChange = {
                    viewModel.onChangeComFamiliaEdicao(
                        state.composicaoFamiliarEdicao.copy(
                            renda = it
                        )
                    )
                }
            )
        }


        Button(
            onClick = {
                if (state.composicaoFamiliarEdicao.nome.isNotEmpty()) {
                    Log.d("ComposicaoFamiliarScreen", "Novo")
                    onChangeCampo(
                        CamposCompFamiliar.ADD_COM_FAMILIA,
                        state.composicaoFamiliarEdicao
                    )
                    viewModel.onChangeComFamiliaEdicao(
                        ComposicaoFamiliar(
                            dataNascimento = Utils.formatData(
                                LocalDate.now()
                            )!!
                        )
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
                text = "Adicionar integrante",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = br.com.casa_guido.ui.theme.Button,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
        }

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Paragraph,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(12.dp)
                .clickable {
                    viewModel.toggleList()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Componentes",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = BackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = "Quantidade: ${listaComposicao.size}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = GreenBlack,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )
            )

            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .padding(start = 10.dp)
                    .clickable {
                        viewModel.toggleList()
                    },
                imageVector = if (state.onVisibleList) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                contentDescription = "Seta para baixo",
                tint = Color.Black
            )
        }
        AnimatedVisibility(
            visible = state.onVisibleList,
            enter = expandVertically(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = shrinkVertically() + fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    listaComposicao.forEach { item ->
                        ItemComponenteFamiliar(
                            nomeComponente = item.nome,
                            parentesco = item.parentesco,
                            onExcluir = {
                                onChangeCampo(
                                    CamposCompFamiliar.DELETE_COM_FAMILIA,
                                    item
                                )
                            },
                            onEditar = {
                                viewModel.onChangeComFamiliaEdicao(item)
                            }
                        )
                    }
                }
            }
        }
    }
}