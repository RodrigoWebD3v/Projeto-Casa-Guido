package br.com.casa_guido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.casa_guido.navigation.NavHost.AuthNavHost
import br.com.casa_guido.ui.theme.Casa_guidoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Casa_guidoTheme {
                val navController = rememberNavController()
                AuthNavHost(navController)
            }
        }
    }
}
