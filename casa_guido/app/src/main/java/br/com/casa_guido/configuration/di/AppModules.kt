package br.com.casa_guido.configuration.di


import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.screens.cadastro.components.CamposPaciente.CamposPacienteViewModel
import br.com.casa_guido.screens.home.HomeViewModel
import br.com.casa_guido.screens.login.LoginViewModel
import br.com.casa_guido.screens.pacientes.PacientesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ViewModelAuthMananger)
    viewModelOf(::PacientesViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CamposPacienteViewModel)
    singleOf(::ClienteApi)
    singleOf(::AuthRepository)
}