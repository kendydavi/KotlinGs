package br.com.fiap.dkendy.agrosatsentinel.domain.repository

import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): Resource<List<WeatherInfo>>
}
