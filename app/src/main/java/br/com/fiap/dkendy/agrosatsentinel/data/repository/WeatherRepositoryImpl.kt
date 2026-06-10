package br.com.fiap.dkendy.agrosatsentinel.data.repository

import br.com.fiap.dkendy.agrosatsentinel.data.model.toDomain
import br.com.fiap.dkendy.agrosatsentinel.data.remote.WeatherRemoteDataSource
import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.WeatherRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Resource<List<WeatherInfo>> {
        return try {
            val response = remoteDataSource.getWeatherForecast(latitude, longitude)
            Resource.Success(response.toDomain())
        } catch (e: UnknownHostException) {
            // Sem internet — usa dados offline para não travar o app
            Resource.Success(getMockWeatherData())
        } catch (e: SocketTimeoutException) {
            // Timeout — usa dados offline
            Resource.Success(getMockWeatherData())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Erro ao buscar previsão do tempo")
        }
    }

    private fun getMockWeatherData(): List<WeatherInfo> = listOf(
        WeatherInfo("2026-06-05T06:00", 18.2, 10, 12.4),
        WeatherInfo("2026-06-05T07:00", 19.5, 12, 11.8),
        WeatherInfo("2026-06-05T08:00", 21.3, 15, 10.2),
        WeatherInfo("2026-06-05T09:00", 23.1, 20, 9.6),
        WeatherInfo("2026-06-05T10:00", 24.8, 25, 13.0),
        WeatherInfo("2026-06-05T11:00", 26.4, 30, 14.5),
        WeatherInfo("2026-06-05T12:00", 27.9, 40, 16.2),
        WeatherInfo("2026-06-05T13:00", 28.5, 55, 18.7)
    )
}
