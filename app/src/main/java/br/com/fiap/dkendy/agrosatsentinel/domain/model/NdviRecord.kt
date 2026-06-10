package br.com.fiap.dkendy.agrosatsentinel.domain.model

data class NdviRecord(
    val date: String,
    val value: Float,
    val status: NdviStatus
)

enum class NdviStatus(val label: String) {
    HEALTHY("Saudável"),
    ATTENTION("Atenção"),
    CRITICAL("Crítico")
}

fun Float.toNdviStatus(): NdviStatus = when {
    this >= 0.65f -> NdviStatus.HEALTHY
    this >= 0.45f -> NdviStatus.ATTENTION
    else -> NdviStatus.CRITICAL
}
