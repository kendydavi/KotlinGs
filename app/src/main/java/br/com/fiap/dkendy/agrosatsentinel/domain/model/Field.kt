package br.com.fiap.dkendy.agrosatsentinel.domain.model

data class Field(
    val id: Int,
    val name: String,
    val crop: String,
    val latitude: Double,
    val longitude: Double,
    val hectares: Double,
    val isFavorite: Boolean = false
)
