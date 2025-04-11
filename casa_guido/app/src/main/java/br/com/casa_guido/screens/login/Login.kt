package br.com.casa_guido.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {

    val context = LocalContext.current
    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val viewModel = koinViewModel<LoginViewModel>()

    val isAuthenticated = viewModelAuthMananger.isAuthenticated.collectAsState()

    LaunchedEffect(isAuthenticated.value) {
        if (isAuthenticated.value) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Button(
            onClick = {
                viewModel.login(
                    "joao1@example.com",
                    "senha123",
                    context
                )
            }
        ) {
            Text(text = "Login")
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