package br.com.casa_guido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.navigation.compose.rememberNavController
import br.com.casa_guido.navigation.root.RootNavHost
import br.com.casa_guido.ui.theme.Casa_guidoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Casa_guidoTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { androidx.compose.material3.Text(text = "Casa Guido") },
                        )
                    }
                ) { padding ->
                    RootNavHost(
                        rootNavHostController = navController,
                        paddingValues = padding
                    )
                }

            }
        }
    }
}
