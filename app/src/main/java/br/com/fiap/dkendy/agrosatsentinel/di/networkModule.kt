package br.com.fiap.dkendy.agrosatsentinel.di

import br.com.fiap.dkendy.agrosatsentinel.data.remote.CopernicusApi
import br.com.fiap.dkendy.agrosatsentinel.data.remote.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val WEATHER_BASE_URL = "https://api.open-meteo.com/v1/"
private const val COPERNICUS_BASE_URL = "https://catalogue.dataspace.copernicus.eu/odata/v1/"

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single(named("weather")) {
        Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("copernicus")) {
        Retrofit.Builder()
            .baseUrl(COPERNICUS_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WeatherApi> {
        get<Retrofit>(named("weather")).create(WeatherApi::class.java)
    }

    single<CopernicusApi> {
        get<Retrofit>(named("copernicus")).create(CopernicusApi::class.java)
    }
}
