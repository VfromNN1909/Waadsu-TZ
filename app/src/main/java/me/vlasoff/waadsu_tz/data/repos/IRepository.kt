package me.vlasoff.waadsu_tz.data.repos

import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData

interface IRepository {
    interface IGeoRepository{
        suspend fun getData(): GeoData
    }
}