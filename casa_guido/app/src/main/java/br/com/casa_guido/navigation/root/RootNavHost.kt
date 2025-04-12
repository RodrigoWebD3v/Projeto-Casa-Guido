package br.com.casa_guido.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import br.com.casa_guido.navigation.navHost.AuthNavHost
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavHost(
    rootNavHostController: NavHostController,
) {

    AuthNavHost(
        navHostController = rootNavHostController
    )

}
