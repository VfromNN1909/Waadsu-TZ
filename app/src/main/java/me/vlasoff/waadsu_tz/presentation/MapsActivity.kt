package me.vlasoff.waadsu_tz.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint
import me.vlasoff.waadsu_tz.R
import me.vlasoff.waadsu_tz.databinding.ActivityMapsBinding
import me.vlasoff.waadsu_tz.domain.entities.map.Routes
import kotlin.math.round

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    /**
     * Не судите строго, первый опыт работы с картами.
     * Получилось бы лучше,если бы не траблы с Api-ключом.
     * Но, что случилось, то случилось. Мой косяк.
     *
     * Пытался сделать всё по красоте,
     * Clean Architecture, MVVM, ViewBinding, Hilt, Coroutines, Retrofit, Google Maps.
     * Также хотел использовать Android Navigation Component, но передумал.
     *
     * Также хотел написать тестов, но, к сожалению, не успел.
     */

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding // стильно, модно молодежно

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

//        buildRoutes(Routes.route1.waypoints)
        drawRoute(map)
//        drawPolygon(map)
    }


    /**
     * Мои потуги использовать Google Directions API оказались тщетны из-за косяка с ключом.
     * Как бы я ни старался, в случае с Directions API, ему не нравится мой API ключ, хотя со всем остальным он работает замечательно.
     * Пробовал я, кстати, 2 библиотеки и писать запрос "ручками" с помощью Retrofit. Не получилось, не фортануло.
     * Мб, через какое-то время и разобрался бы с API-ключом, но его, к превеликому сожалению, нету.
     * Дабы выйти из ситуации буду строить маршрут без Directions API, на полилиниях.
     * Придется поставить побольше маркеров, дабы маршрут оказался точнее.
     */

    private fun drawRoute(map: GoogleMap) {
        val waypoints = Routes.route1.waypoints
        val route = map.addPolyline(
            PolylineOptions()
                .clickable(true)
                .addAll(waypoints)
        )

        // не знаю как правильно писать на карте, поэтому просто поставил маркер с инфой
        val markerInfo = map.addMarker(
            MarkerOptions()
                .position(waypoints[waypoints.size - 1])
                .title("Длина маршрута")
                .snippet("${round(Routes.getRouteLength(waypoints))} метров")
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerInfo.position, 15f))
    }


    /**
     * Попытался отрисовать полигоном данные с сервера(там полигон всей России, что-то получилось не очень, либо какая-то разница в картах, либо я накосячил)
     * Закомментировал, дабы на экране не было лишних линий
     * По поводу парсинга данных с сайта. Не обращайте внимания, что конвертировал данные в активити,
     * пытался делать это в юзкейсе и репозитории, ошибка несоответствия типов, обнаружить оперативно не получилось, все пересмотрел, но нет.
     * Решил подобным способом.
     */
//    private fun drawPolygon(map: GoogleMap) {
//        viewModel.getData().observe(this) { resource ->
//            resource?.let { response ->
//                when(response.status){
//                    Status.SUCCESS -> {
//                        response.data?.let { parseData(it, map) }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, response.message ?: "Что-то пошло не так", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            }
//
//        }
//    }
//
//    private fun parseData(data: GeoData, map: GoogleMap) = lifecycleScope.launch {
//        val allCoords = mutableListOf<LatLng>()
//        data.features.forEach { feature ->
//            feature.geometry.coordinates.forEach { polygon ->
//                polygon.forEach { line ->
//                    line.forEach {
//                        allCoords.add(LatLng(it[0], it[1]))
//                    }
//                }
//            }
//        }
//        launch(Dispatchers.Main) {
//            map.addPolygon(
//                PolygonOptions()
//                    .addAll(allCoords)
//                    .fillColor(Color.CYAN)
//            )
//        }
//
//    }


    /**
     * Попытка запустить Directions API, дубль 3
     */
//    private fun buildRoutes(waypoints: List<LatLng>) {
//        val routing = Routing.Builder()
//            .travelMode(AbstractRouting.TravelMode.WALKING)
//            .withListener(this)
//            .waypoints(waypoints)
//            .build()
//        routing.execute() // async task - отстой
//    }
//
//
//    override fun onRoutingFailure(p0: RouteException?) {
//        if(p0 != null) {
//            Toast.makeText(this, "Error: " + p0.message, Toast.LENGTH_LONG).show();
//        }else {
//            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    override fun onRoutingStart() {
//
//    }
//
//    override fun onRoutingSuccess(p0: ArrayList<Route>?, p1: Int) {
//        p0?.let { routes ->
//            routes.forEach { route ->
//                val line = map.addPolyline(
//                    PolylineOptions()
//                        .clickable(true)
//                        .addAll(route.points)
//                )
//                line.tag = "Длина маршрута - ${route.distanceValue}"
//            }
//        }
//    }
//
//    override fun onRoutingCancelled() {
//
//    }
}