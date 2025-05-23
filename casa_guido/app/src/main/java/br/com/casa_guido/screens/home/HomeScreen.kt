package br.com.casa_guido.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.screens.home.componentes.ListagemAgendamentos
import br.com.casa_guido.screens.home.componentes.grid_home.GridItemData
import br.com.casa_guido.screens.home.componentes.grid_home.GridScreen
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val viewModel = koinViewModel<HomeViewModel>()

    val state by viewModel.uiState.collectAsState()

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("buildScreen.json"))


    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Main
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        LottieAnimation(
            modifier = Modifier
                .fillMaxSize(.9f)
                .background(Color.Transparent),
            composition = composition,
            progress = { progress },
        )

        Text(
            text = "Esta tela está em desenvolvimento",
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }



}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(

    )
}



