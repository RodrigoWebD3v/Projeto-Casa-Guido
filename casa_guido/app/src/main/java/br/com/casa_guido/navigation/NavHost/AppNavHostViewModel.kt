package br.com.casa_guido.navigation.NavHost

import Routes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppNavHostViewModel : ViewModel() {

    private val _paginaAtual = MutableStateFlow<Routes>(Routes.DashBoardScreenRoute)
    val paginaAtualState = _paginaAtual.asStateFlow()

    fun setPaginaAtual(pagina: Routes) {
        _paginaAtual.value = pagina
    }

}