package br.com.fiap.dkendy.agrosatsentinel.data.model

import com.google.gson.annotations.SerializedName

data class CopernicusResponse(
    @SerializedName("value") val products: List<CopernicusProduct>
)

data class CopernicusProduct(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("ContentDate") val contentDate: CopernicusContentDate
)

data class CopernicusContentDate(
    @SerializedName("Start") val start: String,
    @SerializedName("End") val end: String
)
