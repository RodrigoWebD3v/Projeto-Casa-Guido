package br.com.casa_guido.configuration.di


import AppDatabase
import android.app.Application
import androidx.room.Room
import br.com.casa_guido.configuration.ApiViaCepConfig
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.navigation.root.ViewModelAuthMananger
import br.com.casa_guido.repository.ArquivoRepository
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.repository.CirurgiaRepository
import br.com.casa_guido.repository.ComposicaoFamiliarRepository
import br.com.casa_guido.repository.EnderecoRepository
import br.com.casa_guido.repository.HistoricoSaudeRepository
import br.com.casa_guido.repository.PacienteRepository
import br.com.casa_guido.repository.PessoaRepository
import br.com.casa_guido.repository.QuimioRepository
import br.com.casa_guido.repository.RadioRepository
import br.com.casa_guido.repository.SincronizarPacientesRepository
import br.com.casa_guido.repository.SituacaoHabitacionalRepository
import br.com.casa_guido.repository.ViaCepRepository
import br.com.casa_guido.room.dao.ArquivoDao
import br.com.casa_guido.screens.cadastro.CadastroScreenViewModel
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CirurgiaViewModel
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.ComposicaoFamiliarViewModel
import br.com.casa_guido.screens.cadastro.formularios.endereco.EnderecoViewModel
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.IdentificacaoViewModel
import br.com.casa_guido.screens.cadastro.formularios.observacao.CadastroObservacaoViewModel
import br.com.casa_guido.screens.cadastro.formularios.radioterapia.QuimioViewModel
import br.com.casa_guido.screens.cadastro.formularios.radioterapia.RadioViewModel
import br.com.casa_guido.screens.cadastro.formularios.pai.OutroViewModel
import br.com.casa_guido.screens.components.escolaridade.EscolaridadeViewModel

import br.com.casa_guido.screens.home.HomeViewModel
import br.com.casa_guido.screens.login.LoginViewModel
import br.com.casa_guido.screens.main.MainViewModel
import br.com.casa_guido.screens.pacientes.PacientesViewModel
import br.com.casa_guido.service.ArquivoService
import br.com.casa_guido.service.AuthService
import br.com.casa_guido.service.CirurgiaService
import br.com.casa_guido.service.CompartilharArquivoService
import br.com.casa_guido.service.ComposicaoFamiliarService
import br.com.casa_guido.service.CriarPacienteService
import br.com.casa_guido.service.CriarPdfService
import br.com.casa_guido.service.EnderecoService
import br.com.casa_guido.service.HistoricoSaudeService
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.service.PessoaService
import br.com.casa_guido.service.QuimioService
import br.com.casa_guido.service.RadioService
import br.com.casa_guido.service.SincronizarPacientesService
import br.com.casa_guido.service.SituacaoHabitacionalService
import br.com.casa_guido.service.ViaCepService
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module



val authModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ViewModelAuthMananger)

    singleOf(::AuthService)
    singleOf(::AuthRepository)
}


val coreModule = module {
    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "casa_guido_local.db")
            .fallbackToDestructiveMigration(true)
            .build()
    }

    // DAOs
    single { get<AppDatabase>().pessoaDao() }
    single { get<AppDatabase>().enderecoDao() }
    single { get<AppDatabase>().pacienteDao() }
    single { get<AppDatabase>().quimioterapiaDao() }
    single { get<AppDatabase>().radioterapiaDao() }
    single { get<AppDatabase>().cirurgiaDao() }
    single { get<AppDatabase>().tratamentoDao() }
    single { get<AppDatabase>().historicoSaudeDao() }
    single { get<AppDatabase>().situacaoHabitacionalDao() }
    single { get<AppDatabase>().composicaoFamiliarDao() }
    single { get<AppDatabase>().profissionalResponsavelDao() }
    single { get<AppDatabase>().arquivoDao() }

}

val pessoaModule = module {
    viewModelOf(::IdentificacaoViewModel)
    viewModelOf(::EnderecoViewModel)

    singleOf(::PessoaService)
    singleOf(::PessoaRepository)
    singleOf(::EnderecoService)
    singleOf(::EnderecoRepository)
}

val pacienteModule = module {
    viewModelOf(::CadastroScreenViewModel)
    viewModelOf(::PacientesViewModel)
    viewModelOf(::CadastroObservacaoViewModel)
    viewModelOf(::HomeViewModel)

    singleOf(::PacienteService)
    singleOf(::CriarPacienteService)
    singleOf(::PacienteRepository)
    singleOf(::SituacaoHabitacionalService)
    singleOf(::SituacaoHabitacionalRepository)
    singleOf(::ArquivoService)
    singleOf(::ArquivoRepository)
}

val tratamentosModule = module {
    viewModelOf(::QuimioViewModel)
    viewModelOf(::CirurgiaViewModel)
    viewModelOf(::RadioViewModel)
    viewModelOf(::OutroViewModel) // Remover duplicação se for o mesmo caso

    singleOf(::QuimioService)
    singleOf(::QuimioRepository)
    singleOf(::CirurgiaService)
    singleOf(::CirurgiaRepository)
    singleOf(::RadioService)
    singleOf(::RadioRepository)
}

val composicaoFamiliarModule = module {
    viewModelOf(::ComposicaoFamiliarViewModel)

    singleOf(::ComposicaoFamiliarService)
    singleOf(::ComposicaoFamiliarRepository)
}

val historicoSaudeModule = module {
    singleOf(::HistoricoSaudeService)
    singleOf(::HistoricoSaudeRepository)
}

val viaCepModule = module {
    singleOf(::ApiViaCepConfig)
    singleOf(::ViaCepRepository)
    singleOf(::ViaCepService)
}

val infraModule = module {
    viewModelOf(::MainViewModel)

    singleOf(::ClienteApi)
    singleOf(::CriarPdfService)
    singleOf(::CompartilharArquivoService)
    singleOf(::SincronizarPacientesService)
    singleOf(::SincronizarPacientesRepository)
}

val componentesModule = module{
    viewModelOf(::EscolaridadeViewModel)
}

