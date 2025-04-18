package br.com.casa_guido.configuration.di


import AppDatabase
import android.app.Application
import androidx.room.Room
import br.com.casa_guido.configuration.ApiViaCepConfig
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.repository.CirurgiaRepository
import br.com.casa_guido.repository.EnderecoRepository
import br.com.casa_guido.repository.PacienteRepository
import br.com.casa_guido.repository.PessoaRepository
import br.com.casa_guido.repository.QuimioRepository
import br.com.casa_guido.repository.RadioRepository
import br.com.casa_guido.repository.ViaCepRepository
import br.com.casa_guido.screens.cadastro.CadastroScreenViewModel
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CirurgiaViewModel
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.ComposicaoFamiliarViewModel
import br.com.casa_guido.screens.cadastro.formularios.endereco.EnderecoViewModel
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.IdentificacaoViewModel
import br.com.casa_guido.screens.cadastro.formularios.radio.QuimioViewModel
import br.com.casa_guido.screens.cadastro.formularios.radio.RadioViewModel
import br.com.casa_guido.screens.cadastro.formularios.responsavel.OutroViewModel
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.SocioEconomicoViewModel

import br.com.casa_guido.screens.home.HomeViewModel
import br.com.casa_guido.screens.login.LoginViewModel
import br.com.casa_guido.screens.main.MainViewModel
import br.com.casa_guido.screens.pacientes.PacientesViewModel
import br.com.casa_guido.service.AuthService
import br.com.casa_guido.service.CirurgiaService
import br.com.casa_guido.service.CompartilharArquivoService
import br.com.casa_guido.service.CriarPacienteService
import br.com.casa_guido.service.CriarPdfService
import br.com.casa_guido.service.EnderecoService
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.service.PessoaService
import br.com.casa_guido.service.QuimioService
import br.com.casa_guido.service.RadioService
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
    viewModelOf(::OutroViewModel)
    viewModelOf(::RadioViewModel)
    viewModelOf(::QuimioViewModel)
    viewModelOf(::OutroViewModel)




    singleOf(::ClienteApi)
    singleOf(::ApiViaCepConfig)
    singleOf(::ViaCepRepository)
    singleOf(::AuthRepository)
    singleOf(::PessoaRepository)
    singleOf(::PacienteRepository)
    singleOf(::EnderecoRepository)
    singleOf(::QuimioRepository)
    singleOf(::QuimioRepository)
    singleOf(::CirurgiaRepository)
    singleOf(::RadioRepository)
    singleOf(::MainViewModel)



    singleOf(::ViaCepService)
    singleOf(::AuthService)
    singleOf(::PacienteService)
    singleOf(::PessoaService)
    singleOf(::EnderecoService)
    singleOf(::CriarPacienteService)
    singleOf(::QuimioService)
    singleOf(::QuimioService)
    singleOf(::CirurgiaService)
    singleOf(::RadioService)
    singleOf(::CriarPdfService)
    singleOf(::CompartilharArquivoService)


    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "casa_guido_local.db")
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single { get<AppDatabase>().pessoaDao() }
    single { get<AppDatabase>().enderecoDao() }
    single { get<AppDatabase>().pacienteDao() }
    single { get<AppDatabase>().quimioterapiaDao() }
    single { get<AppDatabase>().radioterapiaDao() }
    single { get<AppDatabase>().telefoneDao() }
    single { get<AppDatabase>().cirurgiaDao() }
    single { get<AppDatabase>().tratamentoDao() }
    single { get<AppDatabase>().historicoSaudeDao() }
    single { get<AppDatabase>().situacaoHabitacionalDao() }
    single { get<AppDatabase>().composicaoFamiliarDao() }
    single { get<AppDatabase>().profissionalResponsavelDao() }
    single { get<AppDatabase>().doencasDao() }

}