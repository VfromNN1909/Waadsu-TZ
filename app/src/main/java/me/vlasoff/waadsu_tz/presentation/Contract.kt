package me.vlasoff.waadsu_tz.presentation

import androidx.lifecycle.LiveData
import me.vlasoff.waadsu_tz.data.network.Resource
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData

interface Contract {
    interface IMainViewModel {
        fun getData() : LiveData<Resource<GeoData?>>
    }
}