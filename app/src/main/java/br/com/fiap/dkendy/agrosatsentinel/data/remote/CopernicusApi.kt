package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.CopernicusResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CopernicusApi {

    @GET("Products")
    suspend fun getSentinel2Products(
        @Query("\$filter") filter: String,
        @Query("\$orderby") orderBy: String = "ContentDate/Start desc",
        @Query("\$top") top: Int = 7,
        @Query("\$select") select: String = "Id,Name,ContentDate"
    ): CopernicusResponse
}
