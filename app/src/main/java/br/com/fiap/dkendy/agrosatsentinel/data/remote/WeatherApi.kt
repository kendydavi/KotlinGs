package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,precipitation_probability,windspeed_10m",
        @Query("forecast_days") forecastDays: Int = 3,
        @Query("timezone") timezone: String = "America/Sao_Paulo"
    ): WeatherResponse
}
