package me.vlasoff.waadsu_tz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import me.vlasoff.waadsu_tz.data.network.Resource
import me.vlasoff.waadsu_tz.domain.usecases.GetGeoDataUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGeoDataUseCase: GetGeoDataUseCase
): Contract.IMainViewModel, ViewModel() {
    // получаем данные и сразу оборачиваем их в лайвдату
    override fun getData() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = getGeoDataUseCase.execute()))
        } catch (ex : Exception) {
            emit(Resource.error(data = null, message = ex.message ?: "Ops.. something went wrong.."))
        }
    }
}