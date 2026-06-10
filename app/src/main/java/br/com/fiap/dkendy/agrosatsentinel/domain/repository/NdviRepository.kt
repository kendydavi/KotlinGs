package br.com.fiap.dkendy.agrosatsentinel.domain.repository

import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviRecord

interface NdviRepository {
    suspend fun getNdviRecords(latitude: Double, longitude: Double): Resource<List<NdviRecord>>
}
