package br.com.casa_guido.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {

    val context = LocalContext.current
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val viewModel = koinViewModel<LoginViewModel>()

    val state by viewModelAuthMananger.status.collectAsState()

    var carregando by remember {
        mutableStateOf(false)
    }

    suspend fun login(){
        coroutineScope{
            viewModel.login(
                "joao1@example.com",
                "senha123",
                context
            )
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state) {
        when (state) {
            Resultado.Carregando -> {

            }

            is Resultado.Erro -> {
                println("Erro na view ao fazer login")
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = (state as Resultado.Erro).mensagem,
                        duration = SnackbarDuration.Short,
                        actionLabel = "Fechar"
                    )
                }
                carregando = false
            }

            is Resultado.Sucesso -> {
                println("Caiu na verificacao de sucesso de login")
                onLoginSuccess()
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
                        backgroundColor = if (state is Resultado.Erro) Color.Red else Paragraph
                    )
                }
            )
        },

        ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Button(
                onClick = {
                    carregando = true
                    coroutineScope.launch {
                        login()
                    }
                },
                enabled = !carregando
            ) {
                Text(text = "Login")
            }
        }
    }
}


@Preview
@Composable
private fun LoginPreview() {
    Login(
        modifier = Modifier,
        onLoginSuccess = { }
    )
}