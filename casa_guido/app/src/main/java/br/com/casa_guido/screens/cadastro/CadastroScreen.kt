@file:Suppress("UNREACHABLE_CODE")

package br.com.casa_guido.screens.cadastro


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CadastroCirurgia
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.CadastroComposicaoFamiliar
import br.com.casa_guido.screens.cadastro.formularios.conjuge.CadastroConjuge
import br.com.casa_guido.screens.cadastro.formularios.historicoSaude.CadastroHistoricoSaude
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.CadastroIdentificacaoPaciente
import br.com.casa_guido.screens.cadastro.formularios.observacao.Observacao
import br.com.casa_guido.screens.cadastro.formularios.outro.CadastroOutro
import br.com.casa_guido.screens.cadastro.formularios.radio.CadastroQuimio
import br.com.casa_guido.screens.cadastro.formularios.radio.RadioCadastro
import br.com.casa_guido.screens.cadastro.formularios.responsavel.CadastroResponsavel
import br.com.casa_guido.screens.cadastro.formularios.situacaoHabitacional.CadastroSituacaoHabitacional
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.CadastroSocioEconomico
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class ItemNavBar(
    val texto: String,
    val icon: ImageVector,
    val id: Int
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    pacienteId: String? = null
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = koinViewModel<CadastroScreenViewModel>()
    val paciente by viewModel.paciente.collectAsState()
    val status by viewModel.status.collectAsState()


    LaunchedEffect(pacienteId) {
        if (pacienteId != null) {
            viewModel.getPaciente(id = pacienteId)
        }
    }

    LaunchedEffect(status) {
        when (status) {
            Status.Carregando -> {

            }

            is Status.Desconectado -> {

            }

            is Status.Erro -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Erro ao carregar paciente",
                        duration = SnackbarDuration.Short,
                        actionLabel = "Fechar"
                    )
                }
            }

            Status.SemInteracao -> {

            }

            is Status.Sucesso -> {

            }
        }

    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = if (pacienteId != null) "Editando paciente" else "Criando paciente",
                duration = SnackbarDuration.Short,
                actionLabel = "Fechar"
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        actionColor = GreenBlack,
                        contentColor = GreenBlack,
                        backgroundColor = if (pacienteId != null) Button else Paragraph,
                    )
                }
            )
        },

        topBar = {
            TopAppBarComp(
                itemNavBar = ItemNavBar("Cadastro", Icons.Default.PersonAddAlt, 0),
                arrowBack = true,
                navHostController = navHostController
            )
        },

        ) { innerPadding ->
        val pagerState = rememberPagerState(pageCount = { 12 })
        var percentual by remember { mutableFloatStateOf(.0f) }
        val animatedPercentual by animateFloatAsState(targetValue = percentual, label = "")

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Main),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.Gray)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animatedPercentual)
                        .background(Button)
                )
            }

            LaunchedEffect(pagerState.currentPage) {
                //remover scrolltopage apos finalizar criacao de todo o cadastro
                //pagerState.scrollToPage(12)


                percentual = pagerState.currentPage / (pagerState.pageCount - 1).toFloat()
            }



            HorizontalPager(state = pagerState, beyondViewportPageCount = 12) { page ->
                Box(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            when (page) {
                                0 -> {
                                    CadastroIdentificacaoPaciente(
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeIdentificacaoPaciente(
                                                campo,
                                                valor
                                            )
                                        },
                                        onChangeCampoEndereco = { campo, valor ->
                                            viewModel.onChangeEndereco(campo, valor)
                                        },
                                        numeroTela = page + 1
                                    )
                                }

                                1 -> {
                                    CadastroSocioEconomico(
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeSocioEconomico(
                                                campo,
                                                valor
                                            )
                                        },
                                        numeroTela = page + 1,
                                    )
                                }

                                2 -> {
                                    CadastroCirurgia(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeCirurgia(
                                                campo,
                                                valor
                                            )
                                        },
                                        listaCirurgias = paciente.cirurgias,
                                        numeroTela = page + 1,
                                    )
                                }

                                3 -> {
                                    CadastroQuimio(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeQuimio(
                                                campo,
                                                valor
                                            )
                                        },
                                        listaQuimios = paciente.quimios,
                                        numeroTela = page + 1,
                                    )
                                }

                                4 -> {
                                    RadioCadastro(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeRadio(
                                                campo,
                                                valor
                                            )
                                        },
                                        listaRadios = paciente.radios,
                                        numeroTela = page + 1,
                                    )
                                }

                                5 -> {
                                    CadastroResponsavel(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeResponsavel(campo, valor)
                                        },
                                        responsavel = paciente.responsavel,
                                        onChangeEndereco = { campo, valor ->
                                            viewModel.onChangeEnderecoResponsavel(campo, valor)
                                        },
                                        numeroTela = page + 1,
                                    )
                                }

                                6 -> {
                                    CadastroConjuge(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeConjuge(campo, valor)
                                        },
                                        conjuge = paciente.conjugeResponsavel,
                                        onChangeEndereco = { campo, valor ->
                                            viewModel.onChangeEnderecoConjuge(campo, valor)
                                        },
                                        numeroTela = page + 1,
                                    )
                                }

                                7 -> {
                                    CadastroOutro(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeOutro(campo, valor)
                                        },
                                        outro = paciente.outroResponsavel,
                                        onChangeEnderecoOutro = { campo, valor ->
                                            viewModel.onChangeEnderecoOutro(campo, valor)
                                        },
                                        numeroTela = page + 1,
                                    )
                                }

                                8 -> {
                                    CadastroComposicaoFamiliar(
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeComposicaoFamiliar(
                                                campo,
                                                valor
                                            )
                                        },
                                        numeroTela = page + 1,
                                        listaComposicao = paciente.composicaoFamiliar,
                                    )
                                }

                                9 -> {
                                    CadastroHistoricoSaude(
                                        historicoDoencas = paciente.historicoSaude,
                                        numeroTela = page + 1,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeHistoricoSaude(campo, valor)
                                        }
                                    )
                                }

                                10 -> {
                                    CadastroSituacaoHabitacional(
                                        situacaoHabitacional = paciente.situacaoHabitacional,
                                        numeroTela = page + 1,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeSituacaoHabitacional(campo, valor)
                                        }
                                    )
                                }

                                11 -> {
                                    Observacao(
                                        numeroTela = page + 1,
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeObservacao(
                                                campo, valor
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = if (pagerState.currentPage == 0) Arrangement.End else Arrangement.SpaceBetween
                        ) {
                            if (pagerState.currentPage > 0) {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable(
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(
                                                        pagerState.currentPage.dec()
                                                    )
                                                }
                                            },
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = LocalIndication.current
                                        )
                                ) {
                                    Box(
                                        Modifier
                                            .background(GreenBlack)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.DoubleArrow,
                                            contentDescription = "Proxima tela",
                                            tint = Main,
                                            modifier = Modifier.graphicsLayer {
                                                scaleX = -1f
                                            }
                                        )
                                    }

                                    Box(
                                        Modifier
                                            .background(Paragraph)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "Anterior",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                color = GreenBlack,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Start
                                            )
                                        )
                                    }
                                }
                            }
                            if (pagerState.currentPage < pagerState.pageCount - 1) {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable(
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(
                                                        pagerState.currentPage.inc()
                                                    )
                                                }
                                            },
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = LocalIndication.current
                                        )
                                ) {
                                    Box(
                                        Modifier
                                            .background(Paragraph)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "Proximo",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                color = GreenBlack,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Start
                                            )
                                        )
                                    }
                                    Box(
                                        Modifier
                                            .background(GreenBlack)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.DoubleArrow,
                                            contentDescription = "Proxima tela",
                                            tint = Main,
                                        )
                                    }
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable(
                                            onClick = {
                                                coroutineScope.launch {
                                                    viewModel.save()
                                                    snackbarHostState.showSnackbar(
                                                        message = "Paciente salvo com sucesso",
                                                        duration = SnackbarDuration.Short,
                                                        actionLabel = "Fechar"
                                                    )
                                                }
                                            },
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = LocalIndication.current
                                        )
                                ) {
                                    Box(
                                        Modifier
                                            .background(Paragraph)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "Salvar Paciente",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                color = GreenBlack,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Start
                                            )
                                        )
                                    }
                                    Box(
                                        Modifier
                                            .background(GreenBlack)
                                            .padding(5.dp)
                                            .height(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Save,
                                            contentDescription = "Salvar",
                                            tint = Main,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}