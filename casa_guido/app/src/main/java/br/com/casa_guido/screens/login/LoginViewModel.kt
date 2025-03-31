package br.com.casa_guido.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.repository.AuthRepository

class LoginViewModel(
    private val repository: AuthRepository,
    private val viewModelAuthManager: ViewModelAuthMananger
) : ViewModel() {


    fun login(email: String, password: String, context: Context) {
        viewModelAuthManager.login(context, email, password)
    }


    init {
        //login()
    }
}