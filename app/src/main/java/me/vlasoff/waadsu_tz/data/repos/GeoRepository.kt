package me.vlasoff.waadsu_tz.data.repos

import me.vlasoff.waadsu_tz.data.network.ApiService
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData
import javax.inject.Inject

class GeoRepository @Inject constructor(
    private val service: ApiService
): IRepository.IGeoRepository {
    override suspend fun getData(): GeoData = service.getPoints()
}