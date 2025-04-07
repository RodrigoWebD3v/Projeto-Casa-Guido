package br.com.casa_guido.screens.cadastro


import android.os.Build
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.Cirurgia
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.endereco.Endereco
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.CamposIdentificacao
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.IdentificacaoPaciente
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.CamposSocioEconomico
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.SocioEconomico
import br.com.casa_guido.screens.scaffold_components.TopAppBarComp
import br.com.casa_guido.screens.shared.DataPicker
import br.com.casa_guido.screens.shared.RadioButtonComLabel
import br.com.casa_guido.screens.shared.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class itemNavBar(
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
                itemNavBar = itemNavBar("Cadastro", Icons.Default.PersonAddAlt, 0),
                arrowBack = true,
                navHostController = navHostController
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.save()
                    viewModel.showToast(
                        context = context,
                        message = if (userId != null) "Paciente editado com sucesso" else "Paciente cadastrado com sucesso"
                    )
                },
                containerColor = Button
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Adicionar",
                    tint = GreenBlack
                )
            }
        },



    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Main),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

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
                    selectedItem = selectedItem.intValue == 0,
                    onCollapse = {
                        if (selectedItem.intValue == 0) {
                            selectedItem.intValue = -1
                        } else {
                            selectedItem.intValue = 0
                        }
                    }
                )

                Endereco(
                    paciente = paciente,
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
                    selectedItem = selectedItem.intValue == 1,
                    onCollapse = {
                        if (selectedItem.intValue == 1) {
                            selectedItem.intValue = -1
                        } else {
                            selectedItem.intValue = 1
                        }
                    }
                )

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
                                viewModel.onChangeRemuneracaoOpt(
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
                    selectedItem = selectedItem.intValue == 2,
                    onCollapse = {
                        if (selectedItem.intValue == 2) {
                            selectedItem.intValue = -1
                        } else {
                            selectedItem.intValue = 2
                        }
                    }
                )

                Cirurgia(
                    onChangeCampo = { campo, valor ->
                        when (campo) {
                            CamposEndereco.LOGRADOURO -> TODO()
                            CamposEndereco.NUMERO -> TODO()
                            CamposEndereco.COMPLEMENTO -> TODO()
                            CamposEndereco.BAIRRO -> TODO()
                            CamposEndereco.LOCALIDADE -> TODO()
                            CamposEndereco.UF -> TODO()
                            CamposEndereco.CEP -> TODO()
                        }
                    },
                    selectedItem = selectedItem.intValue == 3,
                    onCollapse = {
                        if (selectedItem.intValue == 3) {
                            selectedItem.intValue = -1
                        } else {
                            selectedItem.intValue = 3
                        }
                    }
                )

            }
        }
    }
}