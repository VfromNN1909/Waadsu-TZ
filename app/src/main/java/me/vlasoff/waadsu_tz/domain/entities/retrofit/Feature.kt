package me.vlasoff.waadsu_tz.domain.entities.retrofit

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)