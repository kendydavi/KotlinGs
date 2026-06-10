package br.com.fiap.dkendy.agrosatsentinel.domain.usecase

import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.WeatherRepository

class GetWeatherByLocationUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Resource<List<WeatherInfo>> {
        if (latitude < -90 || latitude > 90) {
            return Resource.Error("Latitude inválida")
        }
        if (longitude < -180 || longitude > 180) {
            return Resource.Error("Longitude inválida")
        }
        return repository.getWeatherForecast(latitude, longitude)
    }
}
