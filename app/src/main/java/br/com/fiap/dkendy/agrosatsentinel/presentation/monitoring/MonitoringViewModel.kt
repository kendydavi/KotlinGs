package br.com.fiap.dkendy.agrosatsentinel.presentation.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.Field
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviRecord
import br.com.fiap.dkendy.agrosatsentinel.domain.usecase.GetNdviStatusUseCase
import br.com.fiap.dkendy.agrosatsentinel.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MonitoringViewModel(
    private val getNdviStatusUseCase: GetNdviStatusUseCase
) : ViewModel() {

    private val _ndviState = MutableStateFlow<UiState<List<NdviRecord>>>(UiState.Idle)
    val ndviState: StateFlow<UiState<List<NdviRecord>>> = _ndviState

    private val _selectedFieldId = MutableStateFlow(1)
    val selectedFieldId: StateFlow<Int> = _selectedFieldId

    val fieldOptions: List<Pair<Int, String>> = fields().map { it.id to it.name }

    init {
        loadNdvi()
    }

    fun selectField(fieldId: Int) {
        _selectedFieldId.value = fieldId
        loadNdvi()
    }

    fun loadNdvi() {
        val field = fields().find { it.id == _selectedFieldId.value } ?: fields().first()
        viewModelScope.launch {
            _ndviState.value = UiState.Loading
            when (val result = getNdviStatusUseCase(field.latitude, field.longitude)) {
                is Resource.Success -> _ndviState.value = UiState.Success(result.data)
                is Resource.Error -> _ndviState.value = UiState.Error(result.message)
                is Resource.Loading -> _ndviState.value = UiState.Loading
            }
        }
    }

    private fun fields(): List<Field> = listOf(
        Field(1, "Talhão Norte", "Soja", -23.45, -46.55, 150.0),
        Field(2, "Talhão Sul", "Milho", -23.60, -46.70, 200.0),
        Field(3, "Talhão Leste", "Cana-de-Açúcar", -23.40, -46.40, 80.0),
        Field(4, "Talhão Oeste", "Café", -23.55, -46.80, 50.0),
        Field(5, "Talhão Central", "Soja", -23.50, -46.60, 120.0),
        Field(6, "Talhão Reserva", "Pastagem", -23.65, -46.65, 300.0)
    )
}
