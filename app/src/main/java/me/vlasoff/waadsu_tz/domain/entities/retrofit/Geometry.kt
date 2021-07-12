package me.vlasoff.waadsu_tz.domain.entities.retrofit

data class Geometry(
    val coordinates: List<List<List<List<Double>>>>,
    val type: String
)