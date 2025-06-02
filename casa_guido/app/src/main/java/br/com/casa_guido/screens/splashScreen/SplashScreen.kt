package br.com.casa_guido.screens.splashScreen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.configuration.Conexao
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.ui.theme.Button
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Paragraph
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(modifier: Modifier = Modifier, setResultado: (Status) -> Unit) {
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val state by viewModelAuthMananger.status.collectAsState()
    val conexao by viewModelAuthMananger.conexao.collectAsState()

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    val context = LocalContext.current


    suspend fun verificarLogin(context : Context){
        coroutineScope.launch {
            viewModelAuthMananger.refreshToken(context)
        }
    }

    LaunchedEffect(Unit) {
        verificarLogin(context)
    }

    LaunchedEffect(state) {
        when(state){
            Status.Carregando -> {

            }

            is Status.Erro -> {
                setResultado(Status.Erro(""))
            }

            is Status.Sucesso -> {
                setResultado(Status.Sucesso(""))
            }

            Status.SemInteracao -> {

            }

            is Status.Desconectado -> {

            }
        }
    }

    LaunchedEffect(conexao) {
        when (conexao) {
            Conexao.Conectado -> {

            }

            Conexao.SemConexao -> {
                snackbarHostState.showSnackbar(
                    message = "Dispositivo não conectado à internet",
                    actionLabel = "Fechar",
                    duration = SnackbarDuration.Short
                )
            }

            Conexao.SemInformacao -> {

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
                        backgroundColor = if (conexao is Conexao.SemConexao) Button else Paragraph
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Paragraph)
                .padding(paddingValues)
                .clickable {
                    setResultado(
                        Status.Sucesso("")
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxSize(.9f)
                    .background(Color.Transparent),
                composition = composition,
                progress = { progress },
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen(setResultado = {})
}