package br.com.casa_guido.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.configuration.Status.*
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
        Status.Carregando -> {
            SplashScreen() { resultado ->
                viewModelAuthMananger.setStatus(resultado)
            }
        }

        is Status.Erro -> {
            AuthNavHost(
                navHostController = rootNavHostController,
                sucessoAutenticacao = { resultado ->
                    viewModelAuthMananger.setStatus(resultado)
                },
                resultado = state
            )
        }

        is Status.Desconectado -> {
            AuthNavHost(
                navHostController = rootNavHostController,
                sucessoAutenticacao = { resultado ->
                    viewModelAuthMananger.setStatus(resultado)
                },
                resultado = state
            )
        }

        is Status.Sucesso -> {
            AppNavHost(
                navHostController = rootNavHostController,
                onLogout = {
                    viewModelAuthMananger.setStatus(Desconectado("Sessão finalziada com sucesso"))
                },
                mensagemSucesso = state
            )
        }

        Status.SemInteracao -> {
            viewModelAuthMananger.setStatus(Status.Carregando)
        }

        is Status.Alerta -> {

        }
    }
}
