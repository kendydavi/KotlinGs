package br.com.fiap.dkendy.agrosatsentinel.di

import br.com.fiap.dkendy.agrosatsentinel.data.local.SharedPreferencesManager
import br.com.fiap.dkendy.agrosatsentinel.data.remote.NdviRemoteDataSource
import br.com.fiap.dkendy.agrosatsentinel.data.remote.NdviRemoteDataSourceImpl
import br.com.fiap.dkendy.agrosatsentinel.data.remote.WeatherRemoteDataSource
import br.com.fiap.dkendy.agrosatsentinel.data.remote.WeatherRemoteDataSourceImpl
import br.com.fiap.dkendy.agrosatsentinel.data.repository.NdviRepositoryImpl
import br.com.fiap.dkendy.agrosatsentinel.data.repository.WeatherRepositoryImpl
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.NdviRepository
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        SharedPreferencesManager(context = androidContext())
    }

    single<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl(api = get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(remoteDataSource = get())
    }

    single<NdviRemoteDataSource> {
        NdviRemoteDataSourceImpl(api = get())
    }

    single<NdviRepository> {
        NdviRepositoryImpl(remoteDataSource = get())
    }
}
