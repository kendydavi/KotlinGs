package br.com.fiap.dkendy.agrosatsentinel.domain.model

data class Alert(
    val id: Int,
    val fieldName: String,
    val title: String,
    val description: String,
    val type: AlertType,
    val isRead: Boolean = false
)

enum class AlertType(val label: String) {
    RAIN("Chuva"),
    DROUGHT("Seca"),
    FROST("Geada"),
    WIND("Vento"),
    NDVI("NDVI Baixo")
}
