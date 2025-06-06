@file:Suppress("UNREACHABLE_CODE")

package br.com.casa_guido.screens.cadastro


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CadastroCirurgia
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.CadastroComposicaoFamiliar
import br.com.casa_guido.screens.cadastro.formularios.documentos.AdicionarDocumentos
import br.com.casa_guido.screens.cadastro.formularios.mae.CadastroConjuge
import br.com.casa_guido.screens.cadastro.formularios.historicoDeSaudeFamiliar.CadastroHistoricoDeSaudeFamiliar
import br.com.casa_guido.screens.cadastro.formularios.historicoDeSaudePaciente.CadastroHistoricoSaudePaciente
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.CadastroIdentificacaoPaciente
import br.com.casa_guido.screens.cadastro.formularios.observacao.Observacao
import br.com.casa_guido.screens.cadastro.formularios.outro.CadastroOutro
import br.com.casa_guido.screens.cadastro.formularios.radioterapia.CadastroQuimio
import br.com.casa_guido.screens.cadastro.formularios.radioterapia.RadioCadastro
import br.com.casa_guido.screens.cadastro.formularios.pai.CadastroResponsavel
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


    var quantidadePaginas by remember { mutableStateOf(14) }

    var colorSnackBar by remember { mutableStateOf(Button) }


    viewModel._context = LocalContext.current


    LaunchedEffect(pacienteId) {
        if (pacienteId != null) {
            viewModel.getPaciente(id = pacienteId)
        }
    }

    LaunchedEffect(status) {
        when (status) {
            Status.Carregando -> {
                viewModel.updateStatus(Status.SemInteracao)
            }

            is Status.Desconectado -> {
                viewModel.updateStatus(Status.SemInteracao)
            }

            is Status.Erro -> {
                colorSnackBar = Button
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message =  (status as Status.Erro).mensagem,
                        duration = SnackbarDuration.Short,
                        actionLabel = "Fechar"
                    )
                }
                viewModel.updateStatus(Status.SemInteracao)
            }

            Status.SemInteracao -> {
            }

            is Status.Sucesso -> {
                colorSnackBar = Paragraph
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = (status as Status.Sucesso).mensagem,
                        duration = SnackbarDuration.Short,
                        actionLabel = "Fechar"
                    )
                }
                viewModel.updateStatus(Status.SemInteracao)
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
                        backgroundColor = colorSnackBar ,
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
        val pagerState = rememberPagerState(pageCount = { quantidadePaginas })
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



            HorizontalPager(state = pagerState, beyondViewportPageCount = quantidadePaginas) { page ->
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
                                    CadastroHistoricoSaudePaciente(
                                        numeroTela = page + 1,
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeHistoricoSaude(campo, valor)
                                        }
                                    )
                                }

                                3 -> {
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

                                4 -> {
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

                                5 -> {
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

                                6 -> {
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

                                7 -> {
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

                                8 -> {
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

                                9 -> {
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

                                10 -> {
                                    CadastroHistoricoDeSaudeFamiliar(
                                        historicoDoencas = paciente.historicoSaude,
                                        numeroTela = page + 1,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeHistoricoSaude(campo, valor)
                                        }
                                    )
                                }

                                11 -> {
                                    CadastroSituacaoHabitacional(
                                        situacaoHabitacional = paciente.situacaoHabitacional,
                                        numeroTela = page + 1,
                                        onChangeCampo = { campo, valor ->
                                            viewModel.onChangeSituacaoHabitacional(campo, valor)
                                        }
                                    )
                                }

                                12 -> {
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

                                13 -> {
                                    AdicionarDocumentos(
                                        numeroTela = page + 1,
                                    )
                                }

                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = if (pagerState.currentPage == 0) Arrangement.End else Arrangement.SpaceBetween
                        ) {
                            if (pagerState.currentPage != 0) {
                                NavigationButton(
                                    label = "Anterior",
                                    icon = Icons.Default.DoubleArrow,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                        }
                                    },
                                    reverseIcon = true
                                )
                                NavigationButton(
                                    label = "Salvar",
                                    icon = Icons.Default.Save,
                                    onClick = {
                                        coroutineScope.launch {
                                            viewModel.save{
                                                navHostController.popBackStack()
                                            }
                                        }
                                    }
                                )

                                NavigationButton(
                                    label = "Próximo",
                                    icon = Icons.Default.DoubleArrow,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }
                                )
                            } else {
                                NavigationButton(
                                    label = "Salvar",
                                    icon = Icons.Default.Save,
                                    modifier = Modifier.padding(end = 20.dp),
                                    onClick = {
                                        coroutineScope.launch {
                                            viewModel.save{
                                                navHostController.popBackStack()
                                            }
                                        }
                                    }
                                )

                                NavigationButton(
                                    label = "Próximo",
                                    icon = Icons.Default.DoubleArrow,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    reverseIcon: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(Paragraph)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (reverseIcon) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = GreenBlack,
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer { scaleX = -1f }
            )
        }
        Text(
            text = label,
            color = GreenBlack,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 6.dp)
        )
        if (!reverseIcon) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = GreenBlack,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}