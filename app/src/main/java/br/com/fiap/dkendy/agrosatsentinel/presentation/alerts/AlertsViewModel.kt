package br.com.fiap.dkendy.agrosatsentinel.presentation.alerts

import androidx.lifecycle.ViewModel
import br.com.fiap.dkendy.agrosatsentinel.domain.model.Alert
import br.com.fiap.dkendy.agrosatsentinel.domain.model.AlertType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlertsViewModel : ViewModel() {

    private val _alerts = MutableStateFlow(mockAlerts())
    val alerts: StateFlow<List<Alert>> = _alerts

    fun markAsRead(alertId: Int) {
        _alerts.value = _alerts.value.map { alert ->
            if (alert.id == alertId) alert.copy(isRead = true) else alert
        }
    }

    fun markAllAsRead() {
        _alerts.value = _alerts.value.map { it.copy(isRead = true) }
    }

    private fun mockAlerts(): List<Alert> = listOf(
        Alert(1, "Talhão Norte", "Risco de Geada", "Temperatura prevista abaixo de 2°C nas próximas 12 horas.", AlertType.FROST),
        Alert(2, "Talhão Sul", "NDVI Crítico", "Índice NDVI abaixo de 0.45 detectado em 15/05. Verificar possível estresse hídrico.", AlertType.NDVI),
        Alert(3, "Talhão Leste", "Chuva Intensa", "Probabilidade de 85% de chuva acima de 30mm para amanhã.", AlertType.RAIN),
        Alert(4, "Talhão Oeste", "Vento Forte", "Rajadas de vento de até 60 km/h previstas para esta tarde.", AlertType.WIND),
        Alert(5, "Talhão Central", "Alerta de Seca", "Sem precipitação há 15 dias consecutivos. Monitorar irrigação.", AlertType.DROUGHT),
        Alert(6, "Talhão Norte", "NDVI em Atenção", "NDVI = 0.58 na última leitura. Abaixo da média histórica.", AlertType.NDVI)
    )
}
