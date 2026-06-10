package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.CopernicusProduct

interface NdviRemoteDataSource {
    suspend fun getSentinel2Products(latitude: Double, longitude: Double): List<CopernicusProduct>
}
