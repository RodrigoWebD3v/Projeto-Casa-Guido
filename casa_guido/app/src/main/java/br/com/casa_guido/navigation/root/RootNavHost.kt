package br.com.casa_guido.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.navHost.AppNavHost
import br.com.casa_guido.navigation.navHost.AuthNavHost
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavHost(
    rootNavHostController: NavHostController,
    ) {

    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
    val isAuthenticated = viewModelAuthMananger.isAuthenticated.collectAsState()

    LaunchedEffect(
        Unit
    ) { viewModelAuthMananger.refreshToken(rootNavHostController.context) }

    if (isAuthenticated.value) {
        AppNavHost(
            navHostController = rootNavHostController,
        )
    } else {
        AuthNavHost(
            navHostController = rootNavHostController,
        )
    }

//    AppNavHost(
//        navHostController = rootNavHostController,
//    )
}
