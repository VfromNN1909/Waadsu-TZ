package me.vlasoff.waadsu_tz.data.repos

import com.google.android.gms.maps.model.LatLng
import me.vlasoff.waadsu_tz.data.network.ApiService
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData
import javax.inject.Inject

// репозиторий
class GeoRepository @Inject constructor(
    private val service: ApiService
): IRepository.IGeoRepository {
    override suspend fun getData() = service.getPoints()

}