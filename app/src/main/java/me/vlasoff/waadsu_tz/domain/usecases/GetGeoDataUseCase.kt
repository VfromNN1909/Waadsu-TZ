package me.vlasoff.waadsu_tz.domain.usecases

import me.vlasoff.waadsu_tz.data.repos.GeoRepository
import javax.inject.Inject


class GetGeoDataUseCase @Inject constructor(
    private val repository: GeoRepository
) {
    suspend fun execute() = repository.getData()

}