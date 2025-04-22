package br.com.casa_guido.screens.cadastro.formularios.observacao

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun Observacao(
    modifier: Modifier = Modifier,
    numeroTela: Int,
    onChangeCampo: (CamposObservacao, String) -> Unit,
    paciente: Paciente,
) {
    val viewModel = koinViewModel<CadastroObservacaoViewModel>()
    val state by viewModel.uiState.collectAsState()

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
                    "$numeroTela. Demais dados", style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
                Text(
                    "Demais dados", style = TextStyle(
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
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextFieldSimples(
            nomeCampo = "Como conheceu a ONG Guido",
            placeholder = "",
            valorPreenchido = paciente.origen_info_ong,
            paddingValues = PaddingValues(0.dp),
            singleLine = false
        ) {
            onChangeCampo(
                CamposObservacao.COMO_CONHECEU,
                it
            )
        }

        TextFieldSimples(
            nomeCampo = "Descricão da observação",
            placeholder = "",
            valorPreenchido = state.observacaoEdicao,
            paddingValues = PaddingValues(0.dp)
        ) {
            viewModel.onChangeObservacaoEdicao(it)
        }

        Button(
            onClick = {
                if (state.observacaoEdicao.isNotEmpty()) {
                    if (!viewModel.isEdicao.value) {
                        onChangeCampo(
                            CamposObservacao.ADD_OBSERVACAO,
                            state.observacaoEdicao
                        )
                        viewModel.onChangeObservacaoEdicao("")
                    } else {
                        onChangeCampo(
                            CamposObservacao.DELETE_OBSERVACAO,
                            viewModel.indexInEdicao.value.toString()
                        )

                        onChangeCampo(
                            CamposObservacao.ADD_OBSERVACAO,
                            state.observacaoEdicao
                        )

                        viewModel.onChangeIsEdicao(false)
                        viewModel.onChangeObservacaoEdicao("")

                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
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
                text = "Adicionar observação",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = br.com.casa_guido.ui.theme.Button,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
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
                    text = "Observações",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    text = "Quantidade: ${paciente.observacoes.size}",
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
                        .heightIn(min = 0.dp, max = 450.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {
                        paciente.observacoes.forEachIndexed { index, item ->
                            ItemObservacao(
                                textoObservacao = item,
                                onExcluir = {
                                    onChangeCampo(
                                        CamposObservacao.DELETE_OBSERVACAO,
                                        index.toString()
                                    )
                                },
                                onClickListner = {
                                    viewModel.onChangeIsEdicao(true)
                                    viewModel.onChangeIndexInEdicao(index)
                                    viewModel.onChangeObservacaoEdicao(item)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}