package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.WeatherResponse

class WeatherRemoteDataSourceImpl(
    private val api: WeatherApi
) : WeatherRemoteDataSource {

    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherResponse {
        return api.getWeatherForecast(latitude = latitude, longitude = longitude)
    }
}
