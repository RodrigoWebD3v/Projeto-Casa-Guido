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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CirurgiaCadastro
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.ComposicaoFamiliar
import br.com.casa_guido.screens.cadastro.formularios.conjuge.CadastroConjuge
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.endereco.Endereco
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.CamposIdentificacao
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.IdentificacaoPaciente
import br.com.casa_guido.screens.cadastro.formularios.radio.QuimioCadastro
import br.com.casa_guido.screens.cadastro.formularios.radio.RadioCadastro
import br.com.casa_guido.screens.cadastro.formularios.responsavel.CadastroResponsavel
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.CamposSocioEconomico
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.SocioEconomico
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
    userId: String? = null
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val viewModel = koinViewModel<CadastroScreenViewModel>()
    val state by viewModel.uiState.collectAsState()
    val paciente by viewModel.paciente.collectAsState()

    val selectedItem = remember { mutableIntStateOf(-1) }

    val context = LocalContext.current

    LaunchedEffect(userId) {
        if (userId != null) {
            viewModel.getUsuario(id = userId)
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = if (userId != null) "Editando paciente" else "Criando paciente",
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
                        backgroundColor = if (userId != null) Button else Paragraph,
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
        val pagerState = rememberPagerState(pageCount = { 10 })
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
                percentual = pagerState.currentPage / (pagerState.pageCount - 1).toFloat()
            }

            HorizontalPager(state = pagerState, beyondViewportPageCount = 10) { page ->
                Box(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            when (page) {
                                0 -> {
                                    IdentificacaoPaciente(
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            when (campo) {
                                                CamposIdentificacao.NOME -> {
                                                    viewModel.onChangeNome(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.NOME_MAE -> {
                                                    viewModel.onChangeMae(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.NOME_PAI -> {
                                                    viewModel.onChangePai(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.CPF -> {
                                                    viewModel.onChangeCpf(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.RG -> {
                                                    viewModel.onChangeRg(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.CARTAO_SUS -> {
                                                    viewModel.onChangeCartaoSus(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.DATA_NASCIMENTO -> {
                                                    viewModel.onChangeData(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.TELEFONE -> {
                                                    viewModel.onChangeTelefone(
                                                        valor
                                                    )
                                                }

                                                CamposIdentificacao.NOME_RESPONSAVEL -> {
                                                    viewModel.onChangeOutro(
                                                        valor
                                                    )
                                                }

                                            }
                                        },
                                        onCollapse = {
                                            if (selectedItem.intValue == 0) {
                                                selectedItem.intValue = -1
                                            } else {
                                                selectedItem.intValue = 0
                                            }
                                        },
                                    )
                                }

                                1 -> {
                                    Endereco(
                                        pessoa = paciente.pessoa,
                                        onChangeCampo = { campo, valor ->
                                            when (campo) {
                                                CamposEndereco.LOGRADOURO -> {
                                                    viewModel.onChangeLogradouro(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.NUMERO -> {
                                                    viewModel.onChangeNumero(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.COMPLEMENTO -> {
                                                    viewModel.onChangeComplemento(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.BAIRRO -> {
                                                    viewModel.onChangeBairro(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.LOCALIDADE -> {
                                                    viewModel.onChangeLocalidade(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.UF -> {
                                                    viewModel.onChangeUf(
                                                        valor
                                                    )
                                                }

                                                CamposEndereco.CEP -> {
                                                    viewModel.onChangeCep(
                                                        valor
                                                    )
                                                }
                                            }
                                        },
                                        onCollapse = {
                                            if (selectedItem.intValue == 1) {
                                                selectedItem.intValue = -1
                                            } else {
                                                selectedItem.intValue = 1
                                            }
                                        })
                                }

                                2 -> {
                                    SocioEconomico(
                                        paciente = paciente,
                                        onChangeCampo = { campo, valor ->
                                            when (campo) {
                                                CamposSocioEconomico.ESCOLA_ANO -> {
                                                    viewModel.onChangeEscolaAno(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.NOME_ESCOLA -> {
                                                    viewModel.onChangeNomeEscola(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.REMUNERACAO -> {
                                                    viewModel.onChangeRemuneracao(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.REMUNERACAO_OPT -> {
                                                    viewModel.onChangeBpcOpt(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.VALOR_BPC -> {
                                                    viewModel.onChangeValorBPC(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.TAMANHO_ROUPA -> {
                                                    viewModel.onChangeTamanhoRoupa(
                                                        valor
                                                    )
                                                }

                                                CamposSocioEconomico.TAMANHO_CALCADO -> {
                                                    viewModel.onChangeTamanhoCalcado(
                                                        valor
                                                    )
                                                }
                                            }
                                        },
                                        onCollapse = {
                                            if (selectedItem.intValue == 2) {
                                                selectedItem.intValue = -1
                                            } else {
                                                selectedItem.intValue = 2
                                            }
                                        })
                                }

                                3 -> {
                                    CirurgiaCadastro(onChangeCampo = { campo, valor ->
                                        when (campo) {
                                            CamposEndereco.LOGRADOURO -> TODO()
                                            CamposEndereco.NUMERO -> TODO()
                                            CamposEndereco.COMPLEMENTO -> TODO()
                                            CamposEndereco.BAIRRO -> TODO()
                                            CamposEndereco.LOCALIDADE -> TODO()
                                            CamposEndereco.UF -> TODO()
                                            CamposEndereco.CEP -> TODO()
                                        }
                                    }, onCollapse = {
                                        if (selectedItem.intValue == 3) {
                                            selectedItem.intValue = -1
                                        } else {
                                            selectedItem.intValue = 3
                                        }
                                    })

                                }

                                4 -> {
                                    QuimioCadastro(onChangeCampo = { campo, valor ->
                                        when (campo) {
                                            CamposEndereco.LOGRADOURO -> {

                                            }

                                            CamposEndereco.NUMERO -> {

                                            }

                                            CamposEndereco.COMPLEMENTO -> {

                                            }

                                            CamposEndereco.BAIRRO -> {

                                            }

                                            CamposEndereco.LOCALIDADE -> {

                                            }

                                            CamposEndereco.UF -> {

                                            }

                                            CamposEndereco.CEP -> {

                                            }
                                        }
                                    }, onCollapse = {
                                        if (selectedItem.intValue == 3) {
                                            selectedItem.intValue = -1
                                        } else {
                                            selectedItem.intValue = 3
                                        }
                                    })
                                }

                                6 -> {
                                    RadioCadastro(
                                        onChangeCampo = { campo, valor ->
                                            when (campo) {
                                                CamposEndereco.LOGRADOURO -> {

                                                }

                                                CamposEndereco.NUMERO -> {

                                                }

                                                CamposEndereco.COMPLEMENTO -> {

                                                }

                                                CamposEndereco.BAIRRO -> {

                                                }

                                                CamposEndereco.LOCALIDADE -> {

                                                }

                                                CamposEndereco.UF -> {

                                                }

                                                CamposEndereco.CEP -> {

                                                }
                                            }
                                        }, onCollapse = {
                                            if (selectedItem.intValue == 3) {
                                                selectedItem.intValue = -1
                                            } else {
                                                selectedItem.intValue = 3
                                            }
                                        }
                                    )
                                }

                                7 -> {
                                    CadastroResponsavel(onChangeCampo = { campo, valor ->
                                        when (campo) {
                                            CamposEndereco.LOGRADOURO -> {}
                                            CamposEndereco.NUMERO -> {}
                                            CamposEndereco.COMPLEMENTO -> {

                                            }

                                            CamposEndereco.BAIRRO -> {}
                                            CamposEndereco.LOCALIDADE -> {}
                                            CamposEndereco.UF -> {}
                                            CamposEndereco.CEP -> {}
                                        }
                                    }, onCollapse = {
                                        if (selectedItem.intValue == 3) {
                                            selectedItem.intValue = -1
                                        } else {
                                            selectedItem.intValue = 3
                                        }
                                    })
                                }

                                8 -> {
                                    CadastroConjuge(onChangeCampo = { campo, valor ->
                                        when (campo) {
                                            CamposEndereco.LOGRADOURO -> {}
                                            CamposEndereco.NUMERO -> {}
                                            CamposEndereco.COMPLEMENTO -> {

                                            }

                                            CamposEndereco.BAIRRO -> {}
                                            CamposEndereco.LOCALIDADE -> {}
                                            CamposEndereco.UF -> {}
                                            CamposEndereco.CEP -> {}
                                        }
                                    }, onCollapse = {
                                        if (selectedItem.intValue == 3) {
                                            selectedItem.intValue = -1
                                        } else {
                                            selectedItem.intValue = 3
                                        }
                                    })
                                }

                                9 -> {
                                    ComposicaoFamiliar(
                                        onChangeCampo = { valor1, valor2 ->
                                            when (valor1) {
                                                CamposIdentificacao.NOME -> {

                                                }

                                                CamposIdentificacao.NOME_MAE -> {

                                                }

                                                CamposIdentificacao.NOME_PAI -> {

                                                }

                                                CamposIdentificacao.CPF -> {

                                                }

                                                CamposIdentificacao.RG -> {

                                                }

                                                CamposIdentificacao.CARTAO_SUS -> {

                                                }

                                                CamposIdentificacao.DATA_NASCIMENTO -> {

                                                }

                                                CamposIdentificacao.TELEFONE -> {

                                                }

                                                CamposIdentificacao.NOME_RESPONSAVEL -> {

                                                }
                                            }
                                        },
                                        onCollapse = {

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