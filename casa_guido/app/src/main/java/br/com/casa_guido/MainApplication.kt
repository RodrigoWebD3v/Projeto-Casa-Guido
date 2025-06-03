package br.com.casa_guido

import android.app.Application
import br.com.casa_guido.configuration.di.authModule
import br.com.casa_guido.configuration.di.componentesModule
import br.com.casa_guido.configuration.di.composicaoFamiliarModule
import br.com.casa_guido.configuration.di.coreModule
import br.com.casa_guido.configuration.di.historicoSaudeModule
import br.com.casa_guido.configuration.di.infraModule
import br.com.casa_guido.configuration.di.pacienteModule
import br.com.casa_guido.configuration.di.pessoaModule
import br.com.casa_guido.configuration.di.tratamentosModule
import br.com.casa_guido.configuration.di.viaCepModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModule,
                authModule,
                pessoaModule,
                pacienteModule,
                tratamentosModule,
                composicaoFamiliarModule,
                historicoSaudeModule,
                viaCepModule,
                infraModule,
                componentesModule
            )
        }
    }
}
