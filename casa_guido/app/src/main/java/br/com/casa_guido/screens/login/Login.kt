package br.com.casa_guido.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun Login(modifier: Modifier = Modifier) {

    val viewModel = koinViewModel<LoginViewModel>()
    val data by viewModel.data.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        data?.let { Text(it.token) }
        data?.let { Text(it.refreshToken) }
    }
}