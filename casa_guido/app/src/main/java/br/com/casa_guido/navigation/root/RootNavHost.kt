package br.com.casa_guido.navigation.root

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.NavHost.AppNavHost
import br.com.casa_guido.navigation.NavHost.AuthNavHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavHost(
    rootNavHostController: NavHostController,
    paddingValues: PaddingValues,
    ) {

//    val viewModelAuthMananger = koinViewModel<ViewModelAuthMananger>()
//    val isAuthenticated = viewModelAuthMananger.isAuthenticated.collectAsState()
//
//    LaunchedEffect(
//        Unit
//    ) { viewModelAuthMananger.refreshToken(rootNavHostController.context) }
//
//    if (isAuthenticated.value) {
//        AppNavHost(
//            navHostController = rootNavHostController,
//            paddingValues = paddingValues
//        )
//    } else {
//        AuthNavHost(
//            navHostController = rootNavHostController,
//            paddingValues = paddingValues
//        )
//    }

    AppNavHost(
        navHostController = rootNavHostController,
        paddingValues = paddingValues
    )
}
