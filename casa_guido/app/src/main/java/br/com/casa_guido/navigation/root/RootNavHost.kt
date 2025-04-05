package br.com.casa_guido.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.navHost.AppNavHost

@Composable
fun RootNavHost(
    rootNavHostController: NavHostController,
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
    )
}
