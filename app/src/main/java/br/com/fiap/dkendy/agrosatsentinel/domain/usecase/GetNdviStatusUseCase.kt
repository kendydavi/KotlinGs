package br.com.fiap.dkendy.agrosatsentinel.domain.usecase

import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviRecord
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.NdviRepository

class GetNdviStatusUseCase(
    private val repository: NdviRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Resource<List<NdviRecord>> {
        return repository.getNdviRecords(latitude, longitude)
    }
}
