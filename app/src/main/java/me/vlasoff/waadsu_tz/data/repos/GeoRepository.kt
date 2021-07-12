package me.vlasoff.waadsu_tz.data.repos

import com.google.android.gms.maps.model.LatLng
import me.vlasoff.waadsu_tz.data.network.ApiService
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData
import javax.inject.Inject

class GeoRepository @Inject constructor(
    private val service: ApiService
): IRepository.IGeoRepository {
    override suspend fun getData() = parseCoordinates(service.getPoints())

    private fun parseCoordinates(data: GeoData): List<LatLng> {
        val allCoords = mutableListOf<LatLng>()
        data.features.forEach { feature ->
            feature.geometry.coordinates.forEach { polygon ->
                polygon.forEach { line ->
                    line.forEach {
                        allCoords.add(LatLng(it[0], it[1]))
                    }
                }
            }
        }
        return allCoords
    }
}