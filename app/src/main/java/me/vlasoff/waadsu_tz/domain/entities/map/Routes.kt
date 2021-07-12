package me.vlasoff.waadsu_tz.domain.entities.map

import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class Routes(
    val waypoints: List<LatLng>,
) {
    /**
     * Смоделируем ситуацию.
     * Это данные, которые мы взяли с сервера и распарсили.
     * Теперь нам надо их отобразить в виде маршрута.
     */
    companion object {
        val route1 = Routes(
            listOf(
                LatLng(56.26089769760785, 43.97969085355763),
                LatLng(56.260606958353506, 43.983393032740565),
                LatLng(56.260404703661116, 43.98602552491516),
                LatLng(56.26021930258277, 43.988923542207516),
                LatLng(56.26017716584984, 43.98923458595149),
                LatLng(56.26013924275052, 43.98943941963654),
                LatLng(56.261192648212145, 43.994127835073435),
                LatLng(56.26132748202079, 43.99431749588436),
                LatLng(56.261323268471585, 43.994795441149485),
                LatLng(56.261786756115455, 43.99649480206924),
                LatLng(56.26231106444442, 43.99821002249076),
                LatLng(56.262805633765424, 44.0000982976405), //
                LatLng(56.263157191222895, 43.999808619054846),
                LatLng(56.27564432274657, 43.989229986687306),
                LatLng(56.27569793236576, 43.9889939522925),
                LatLng(56.27569793236576, 43.9889939522925),
                LatLng(56.27479847212973, 43.98867208721599),
                LatLng(56.27464955283325, 43.98831803562379),
                LatLng(56.27442915121038, 43.986944744599505),
                LatLng(56.274530416984184, 43.98533541921046),
                LatLng(56.27526905331114, 43.977599928394646),
                LatLng(56.27338073689724, 43.97656996013781),
                LatLng(56.269669357599405, 43.975840399288245),
                LatLng(56.26162583036446, 43.97366244448077),
                LatLng(56.26134576518964, 43.97531468523637),
                LatLng(56.26117891687594, 43.976956197149356),
                LatLng(56.26089769760785, 43.97969085355763),
            )
        )
        fun getRouteLength(waypoints: List<LatLng>): Float {
            var totalLen = 0f
            for (i in 0 until waypoints.size - 1) {
                val res = FloatArray(1)
                val start = waypoints[i]
                val dest = waypoints[i + 1]
                Location.distanceBetween(start.latitude, start.longitude, dest.latitude,dest.longitude,res)
                totalLen += res[0]
            }
            return totalLen
        }


    }

}
