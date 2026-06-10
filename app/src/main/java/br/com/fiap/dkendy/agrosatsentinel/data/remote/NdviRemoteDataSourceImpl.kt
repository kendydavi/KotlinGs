package br.com.fiap.dkendy.agrosatsentinel.data.remote

import br.com.fiap.dkendy.agrosatsentinel.data.model.CopernicusProduct
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NdviRemoteDataSourceImpl(
    private val api: CopernicusApi
) : NdviRemoteDataSource {

    override suspend fun getSentinel2Products(
        latitude: Double,
        longitude: Double
    ): List<CopernicusProduct> {
        val sixMonthsAgo = LocalDate.now()
            .minusMonths(6)
            .format(DateTimeFormatter.ISO_LOCAL_DATE)

        // WKT usa POINT(longitude latitude)
        val filter = "Collection/Name eq 'SENTINEL-2' and " +
            "OData.CSC.Intersects(area=geography'SRID=4326;POINT($longitude $latitude)') and " +
            "ContentDate/Start gt ${sixMonthsAgo}T00:00:00.000Z"

        return api.getSentinel2Products(filter = filter).products
    }
}
