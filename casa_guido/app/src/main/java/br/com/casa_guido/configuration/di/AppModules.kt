package br.com.casa_guido.configuration.di


import AppDatabase
import android.app.Application
import androidx.room.Room
import br.com.casa_guido.configuration.ApiViaCepConfig
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.repository.ViaCepRepository
import br.com.casa_guido.screens.cadastro.CadastroScreenViewModel
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CirurgiaViewModel
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.ComposicaoFamiliarViewModel
import br.com.casa_guido.screens.cadastro.formularios.endereco.EnderecoViewModel
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.IdentificacaoViewModel
import br.com.casa_guido.screens.cadastro.formularios.responsavel.ResponsavelViewModel
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.SocioEconomicoViewModel

import br.com.casa_guido.screens.home.HomeViewModel
import br.com.casa_guido.screens.login.LoginViewModel
import br.com.casa_guido.screens.pacientes.PacientesViewModel
import br.com.casa_guido.service.AuthService
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.service.ViaCepService
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ViewModelAuthMananger)
    viewModelOf(::PacientesViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CadastroScreenViewModel)
    viewModelOf(::CirurgiaViewModel)
    viewModelOf(::ComposicaoFamiliarViewModel)
    viewModelOf(::SocioEconomicoViewModel)
    viewModelOf(::EnderecoViewModel)
    viewModelOf(::IdentificacaoViewModel)
    viewModelOf(::ResponsavelViewModel)

    singleOf(::ClienteApi)
    singleOf(::ApiViaCepConfig)
    singleOf(::ViaCepRepository)
    singleOf(::ViaCepService)
    singleOf(::AuthRepository)
    singleOf(::AuthService)
    singleOf(::PacienteService)


    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "casa_guido_local.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }
    single { get<AppDatabase>().pessoaDao() }
    single { get<AppDatabase>().enderecoDao() }
    single { get<AppDatabase>().pacienteDao() }
    single { get<AppDatabase>().responsavelDao() }
    single { get<AppDatabase>().conjugeDao() }
    single { get<AppDatabase>().outroResponsavelDao() }
    single { get<AppDatabase>().cirurgiaDao() }
    single { get<AppDatabase>().tratamentoDao() }
    single { get<AppDatabase>().historicoSaudeDao() }
    single { get<AppDatabase>().situacaoHabitacionalDao() }
    single { get<AppDatabase>().composicaoFamiliarDao() }

}