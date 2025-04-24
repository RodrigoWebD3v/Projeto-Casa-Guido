package br.com.casa_guido.screens.login

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.configuration.Conexao
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Login(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit, resultado: Status) {

    val context = LocalContext.current
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()

    val state by viewModelAuthMananger.status.collectAsState()
    val conexao by viewModelAuthMananger.conexao.collectAsState()

    var carregando by remember {
        mutableStateOf(false)
    }

    var corSnackbar by remember {
        mutableStateOf(Paragraph)
    }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    suspend fun login(
        context: Context,
        email: String = "joao1@example.com",
        password: String = "senha123"
    ) {
        coroutineScope {
            viewModelAuthMananger.login(
                email = email,
                password = password,
                context = context
            )
        }
    }

    LaunchedEffect(state) {
        when (state) {
            Status.Carregando -> {
                carregando = true
            }

            is Status.Erro -> {
                corSnackbar = Button
                carregando = false
                snackbarHostState.showSnackbar(
                    message = "Erro ao realizar login",
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
                viewModelAuthMananger.setStatus(Status.SemInteracao)
            }

            is Status.Sucesso -> {
                corSnackbar = Paragraph
                snackbarHostState.showSnackbar(
                    message = "Login realizado com sucesso",
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
                onLoginSuccess()
            }

            Status.SemInteracao -> {

            }

            is Status.Desconectado -> TODO()
        }
    }

    LaunchedEffect(conexao) {
        when (conexao) {
            Conexao.Conectado -> {
            }

            Conexao.SemConexao -> {
                corSnackbar = Color.Red
                snackbarHostState.showSnackbar(
                    message = "Dispositivo não conectado a internet",
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
            }

            Conexao.SemInformacao -> {

            }
        }
    }

    LaunchedEffect(Unit) {
        when (resultado) {
            Status.Carregando -> {

            }

            is Status.Desconectado -> {
                corSnackbar = Paragraph
                snackbarHostState.showSnackbar(
                    message = "Sessão finalizada com sucesso",
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
            }

            is Status.Erro -> {
            }

            Status.SemInteracao -> {
                viewModelAuthMananger.setStatus(Status.Carregando)
            }

            is Status.Sucesso -> {
            }
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
                        backgroundColor = corSnackbar
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(.9f)
                    .background(Color.Transparent)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldSimples(
                    placeholder = "email",
                    onChange = {},
                    valorPreenchido = "joao1@example.com",
                    nomeCampo = "Email"
                )

                TextFieldSimples(
                    placeholder = "password",
                    onChange = {},
                    visualTransformation = PasswordVisualTransformation(),
                    valorPreenchido = "senha123",
                    nomeCampo = "Senha"
                )

                Button(
                    onClick = {
                        carregando = true
                        coroutineScope.launch {
                            login(context)
                        }
                    },
                    enabled = !carregando,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                       Paragraph
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 10.dp
                    )
                ) {
                    if (!carregando) {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.body1,
                            color = GreenBlack
                        )
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = GreenBlack,
                            strokeWidth = 2.dp
                        )
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun LoginPreview() {
    Login(
        modifier = Modifier,
        onLoginSuccess = { },
        resultado = Status.SemInteracao
    )
}