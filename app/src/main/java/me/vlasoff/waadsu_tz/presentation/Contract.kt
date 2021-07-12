package me.vlasoff.waadsu_tz.presentation

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import me.vlasoff.waadsu_tz.data.network.Resource
import me.vlasoff.waadsu_tz.domain.entities.retrofit.GeoData

// контракт для вьюмоделей
interface Contract {
    interface IMainViewModel {
        fun getData() : LiveData<Resource<GeoData?>>
    }
}