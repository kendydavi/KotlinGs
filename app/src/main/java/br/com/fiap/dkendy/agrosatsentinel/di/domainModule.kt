package br.com.fiap.dkendy.agrosatsentinel.di

import br.com.fiap.dkendy.agrosatsentinel.domain.usecase.GetNdviStatusUseCase
import br.com.fiap.dkendy.agrosatsentinel.domain.usecase.GetWeatherByLocationUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetWeatherByLocationUseCase(repository = get())
    }

    factory {
        GetNdviStatusUseCase(repository = get())
    }
}
