package br.com.fiap.dkendy.agrosatsentinel.domain.model

data class WeatherInfo(
    val time: String,
    val temperature: Double,
    val precipitationProbability: Int,
    val windSpeed: Double
)
