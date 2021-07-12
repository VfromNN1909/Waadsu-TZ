package me.vlasoff.waadsu_tz.presentation

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint
import me.vlasoff.waadsu_tz.R
import me.vlasoff.waadsu_tz.data.network.Status
import me.vlasoff.waadsu_tz.databinding.ActivityMapsBinding
import me.vlasoff.waadsu_tz.domain.entities.map.Routes
import kotlin.math.round

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

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
        drawPolygon(map)
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

        val markerInfo = map.addMarker(
            MarkerOptions()
                .position(waypoints[waypoints.size - 1])
                .title("Длина маршрута")
                .snippet("${round(Routes.getRouteLength(waypoints))} метров")
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerInfo.position, 15f))
    }

    private fun drawPolygon(map: GoogleMap) {
        viewModel.getData().observe(this) { resource ->
            resource?.let { response ->
                when(response.status){
                    Status.SUCCESS -> {
                        map.addPolygon(
                            PolygonOptions()
                                .addAll(response.data)
                                .fillColor(Color.CYAN)
                        )
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, response.message ?: "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }


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