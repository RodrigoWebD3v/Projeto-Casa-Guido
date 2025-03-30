package br.com.casa_guido.di


import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.repository.LoginRepository
import br.com.casa_guido.screens.login.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    singleOf(::ClienteApi)
    singleOf(::LoginRepository)

}