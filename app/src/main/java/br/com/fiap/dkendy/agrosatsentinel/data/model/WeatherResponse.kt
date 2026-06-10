package br.com.fiap.dkendy.agrosatsentinel.data.model

import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val hourly: HourlyData
)

data class HourlyData(
    val time: List<String>,
    @SerializedName("temperature_2m") val temperature2m: List<Double>,
    @SerializedName("precipitation_probability") val precipitationProbability: List<Int>,
    @SerializedName("windspeed_10m") val windspeed10m: List<Double>
)

fun WeatherResponse.toDomain(): List<WeatherInfo> {
    val size = minOf(
        hourly.time.size,
        hourly.temperature2m.size,
        hourly.precipitationProbability.size,
        hourly.windspeed10m.size,
        24 // Apenas as primeiras 24 horas
    )
    return (0 until size).map { i ->
        WeatherInfo(
            time = hourly.time[i],
            temperature = hourly.temperature2m[i],
            precipitationProbability = hourly.precipitationProbability[i],
            windSpeed = hourly.windspeed10m[i]
        )
    }
}
