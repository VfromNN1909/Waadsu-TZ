package me.vlasoff.waadsu_tz.data.repos

import com.google.android.gms.maps.model.LatLng

interface IRepository {
    interface IGeoRepository{
        suspend fun getData(): List<LatLng>
    }
}