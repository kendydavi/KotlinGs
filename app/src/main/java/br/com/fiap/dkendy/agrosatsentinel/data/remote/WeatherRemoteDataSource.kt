package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.WeatherResponse

interface WeatherRemoteDataSource {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherResponse
}
