package br.com.casa_guido.screens.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.ui.theme.Paragraph
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.coroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onSuccessLogin: () -> Unit, onLogin: () -> Unit) {
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val state by viewModelAuthMananger.status.collectAsState()

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))

    val composeInteracao by
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = ,
        isPlaying = true
    )

    val context = LocalContext.current

    suspend fun verificaLogin(){
        coroutineScope {
            viewModelAuthMananger.refreshToken(context)
        }
    }

    LaunchedEffect(Unit) {
        verificaLogin()
    }

    LaunchedEffect(state, ) {
        when (state) {
            Resultado.Carregando -> {

            }

            is Resultado.Erro -> {

                   // onLogin()


            }

            is Resultado.Sucesso -> {
               // onSuccessLogin()
            }
        }
    }

    LaunchedEffect(progress) {
        println(
            "iteracao $progress"
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Paragraph),
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

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen(onSuccessLogin = {}, onLogin = {})
}