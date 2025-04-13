package br.com.casa_guido.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.navigation.navHost.AppNavHost
import br.com.casa_guido.navigation.navHost.AuthNavHost
import br.com.casa_guido.screens.splashScreen.SplashScreen
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavHost(
    rootNavHostController: NavHostController,
) {

    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val state by viewModelAuthMananger.status.collectAsState()

    when (state) {
        Resultado.Carregando -> {
            SplashScreen() { resultado ->
                viewModelAuthMananger.setStatus(resultado)
            }
        }

        is Resultado.Erro -> {
            AuthNavHost(
                navHostController = rootNavHostController,
                sucessoAutenticacao = { resultado ->
                    viewModelAuthMananger.setStatus(resultado)
                },
                resultado = state
            )
        }

        is Resultado.Desconectado -> {
            AuthNavHost(
                navHostController = rootNavHostController,
                sucessoAutenticacao = { resultado ->
                    viewModelAuthMananger.setStatus(resultado)
                },
                resultado = state
            )
        }

        is Resultado.Sucesso -> {
            AppNavHost(
                navHostController = rootNavHostController,
                onLogout = {
                    viewModelAuthMananger.setStatus(Resultado.Desconectado("Sessão finalziada com sucesso"))
                }
            )
        }

        Resultado.SemInteracao -> {
            viewModelAuthMananger.setStatus(Resultado.Carregando)
        }


    }
}
