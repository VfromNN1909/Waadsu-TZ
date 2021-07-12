package me.vlasoff.waadsu_tz.domain.usecases

import com.google.android.gms.maps.model.LatLng
import me.vlasoff.waadsu_tz.data.repos.GeoRepository
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData
import javax.inject.Inject

class GetGeoDataUseCase @Inject constructor(
    private val repository: GeoRepository
) {
    suspend fun execute() = repository.getData()


}