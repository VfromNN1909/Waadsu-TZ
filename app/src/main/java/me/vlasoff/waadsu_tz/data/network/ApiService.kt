package me.vlasoff.waadsu_tz.data.network

import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData
import retrofit2.http.GET

interface ApiService {
    @GET("russia.geo.json")
    suspend fun getPoints(): GeoData
}