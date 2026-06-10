package br.com.fiap.dkendy.agrosatsentinel.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo
import br.com.fiap.dkendy.agrosatsentinel.domain.usecase.GetWeatherByLocationUseCase
import br.com.fiap.dkendy.agrosatsentinel.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Coordenadas de São Paulo como padrão do dashboard
private const val DEFAULT_LATITUDE = -23.5505
private const val DEFAULT_LONGITUDE = -46.6333

class HomeViewModel(
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<UiState<List<WeatherInfo>>>(UiState.Idle)
    val weatherState: StateFlow<UiState<List<WeatherInfo>>> = _weatherState

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            _weatherState.value = UiState.Loading
            when (val result = getWeatherByLocationUseCase(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)) {
                is Resource.Success -> _weatherState.value = UiState.Success(result.data)
                is Resource.Error -> _weatherState.value = UiState.Error(result.message)
                is Resource.Loading -> _weatherState.value = UiState.Loading
            }
        }
    }
}
