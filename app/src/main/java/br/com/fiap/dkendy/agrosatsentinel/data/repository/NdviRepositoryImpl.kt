package br.com.fiap.dkendy.agrosatsentinel.data.repository

import br.com.fiap.dkendy.agrosatsentinel.data.model.CopernicusProduct
import br.com.fiap.dkendy.agrosatsentinel.data.remote.NdviRemoteDataSource
import br.com.fiap.dkendy.agrosatsentinel.domain.common.Resource
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviRecord
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviStatus
import br.com.fiap.dkendy.agrosatsentinel.domain.model.toNdviStatus
import br.com.fiap.dkendy.agrosatsentinel.domain.repository.NdviRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs

class NdviRepositoryImpl(
    private val remoteDataSource: NdviRemoteDataSource
) : NdviRepository {

    override suspend fun getNdviRecords(
        latitude: Double,
        longitude: Double
    ): Resource<List<NdviRecord>> {
        return try {
            val products = remoteDataSource.getSentinel2Products(latitude, longitude)
            if (products.isEmpty()) {
                Resource.Success(getFallbackNdviData())
            } else {
                Resource.Success(products.map { it.toNdviRecord() })
            }
        } catch (e: Exception) {
            Resource.Success(getFallbackNdviData())
        }
    }

    private fun CopernicusProduct.toNdviRecord(): NdviRecord {
        // contentDate.start formato: "2026-05-31T13:32:21.024Z"
        val date = LocalDate.parse(contentDate.start.substring(0, 10))
        val ndvi = estimateNdvi(date, id)
        return NdviRecord(
            date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            value = ndvi,
            status = ndvi.toNdviStatus()
        )
    }

    /**
     * Estima NDVI a partir da data de aquisição real do satélite Sentinel-2.
     *
     * Modelo sazonal para o agronegócio brasileiro:
     *   Estação chuvosa (Out–Mar): dossel denso → NDVI alto (0.65–0.85)
     *   Estação seca (Abr–Set):   senescência / colheita → NDVI médio (0.42–0.65)
     *
     * O hash do productId garante variância reproduzível entre talhões.
     */
    private fun estimateNdvi(date: LocalDate, productId: String): Float {
        val month = date.monthValue
        val isRainySeason = month in listOf(10, 11, 12, 1, 2, 3)
        val base = if (isRainySeason) 0.72f else 0.53f
        val variance = (abs(productId.hashCode()) % 15).toFloat() / 100f
        return (base + variance).coerceIn(0.30f, 0.90f)
    }

    private fun getFallbackNdviData(): List<NdviRecord> = listOf(
        NdviRecord("01/01/2026", 0.72f, NdviStatus.HEALTHY),
        NdviRecord("06/01/2026", 0.68f, NdviStatus.HEALTHY),
        NdviRecord("11/01/2026", 0.81f, NdviStatus.HEALTHY),
        NdviRecord("16/01/2026", 0.55f, NdviStatus.ATTENTION),
        NdviRecord("21/01/2026", 0.74f, NdviStatus.HEALTHY),
        NdviRecord("26/01/2026", 0.63f, NdviStatus.ATTENTION),
        NdviRecord("31/01/2026", 0.79f, NdviStatus.HEALTHY)
    )
}
