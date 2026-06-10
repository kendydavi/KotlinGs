package br.com.fiap.dkendy.agrosatsentinel.di

import br.com.fiap.dkendy.agrosatsentinel.presentation.alerts.AlertsViewModel
import br.com.fiap.dkendy.agrosatsentinel.presentation.field.FieldDetailViewModel
import br.com.fiap.dkendy.agrosatsentinel.presentation.field.FieldListViewModel
import br.com.fiap.dkendy.agrosatsentinel.presentation.home.HomeViewModel
import br.com.fiap.dkendy.agrosatsentinel.presentation.monitoring.MonitoringViewModel
import br.com.fiap.dkendy.agrosatsentinel.presentation.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        OnboardingViewModel(sharedPreferencesManager = get())
    }

    viewModel {
        HomeViewModel(getWeatherByLocationUseCase = get())
    }

    viewModel {
        FieldListViewModel()
    }

    viewModel { parameters ->
        FieldDetailViewModel(
            fieldId = parameters.get(),
            getWeatherByLocationUseCase = get(),
            getNdviStatusUseCase = get()
        )
    }

    viewModel {
        MonitoringViewModel(getNdviStatusUseCase = get())
    }

    viewModel {
        AlertsViewModel()
    }
}
