package me.vlasoff.waadsu_tz.data.repos

import com.google.android.gms.maps.model.LatLng
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData

interface IRepository {
    interface IGeoRepository{
        suspend fun getData(): GeoData
    }
}