package br.com.fiap.dkendy.agrosatsentinel

import android.app.Application
import br.com.fiap.dkendy.agrosatsentinel.di.dataModule
import br.com.fiap.dkendy.agrosatsentinel.di.domainModule
import br.com.fiap.dkendy.agrosatsentinel.di.networkModule
import br.com.fiap.dkendy.agrosatsentinel.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AgroSatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AgroSatApplication)

            modules(
                networkModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}
