package br.com.casa_guido.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Home(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Box(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
    }

}

@Preview
@Composable
private fun HomePreview() {
    Home(
        modifier = Modifier,
        paddingValues = PaddingValues(0.dp)
    )
}