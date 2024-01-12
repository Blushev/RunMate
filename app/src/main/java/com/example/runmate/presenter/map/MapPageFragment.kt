package com.example.runmate.presenter.map

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.BuildConfig
import com.example.runmate.R
import com.example.runmate.databinding.FragmentMapPageBinding
import com.example.runmate.di.appComponent
import com.tomtom.quantity.Distance
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.location.LocationProvider
import com.tomtom.sdk.location.android.AndroidLocationProvider
import com.tomtom.sdk.location.android.AndroidLocationProviderConfig
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.TomTomMap
import com.tomtom.sdk.map.display.camera.CameraOptions
import com.tomtom.sdk.map.display.common.WidthByZoom
import com.tomtom.sdk.map.display.location.LocationMarkerOptions
import com.tomtom.sdk.map.display.route.Instruction
import com.tomtom.sdk.map.display.route.RouteOptions
import com.tomtom.sdk.map.display.ui.MapFragment
import com.tomtom.sdk.routing.RoutePlanningCallback
import com.tomtom.sdk.routing.RoutePlanningResponse
import com.tomtom.sdk.routing.RoutingFailure
import com.tomtom.sdk.routing.online.OnlineRoutePlanner
import com.tomtom.sdk.routing.options.Itinerary
import com.tomtom.sdk.routing.options.RoutePlanningOptions
import com.tomtom.sdk.routing.options.calculation.CostModel
import com.tomtom.sdk.routing.options.calculation.RouteType
import com.tomtom.sdk.vehicle.Vehicle
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class MapPageFragment: Fragment(R.layout.fragment_map_page) {
    private val binding: FragmentMapPageBinding by viewBinding()

    private var backToHomeListener: (() -> Unit)? = null

    private lateinit var tomTomMap : TomTomMap
    private lateinit var locationProvider: LocationProvider

    private val apiKey = BuildConfig.TOMTOM_API_KEY

    private val REQUEST_LOCATION_PERMISSION = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLocationPermissions(requireContext())

        val mapOptions = MapOptions(mapKey = apiKey)
        val mapFragment = MapFragment.newInstance(mapOptions)
        childFragmentManager.beginTransaction()
            .replace(R.id.map_container, mapFragment)
            .commit()

        mapFragment.getMapAsync { map ->
            tomTomMap = map
            Log.d("MainActivity", "TomTomMap initialized successfully")
            locationProvider = createAndroidLocationProvider(requireContext())
            enableUserLocation()
        }

        binding.mapPageButtonBack.setOnClickListener {
            backToHomeListener?.invoke()
            true
        }
    }

    private fun checkLocationPermissions(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            enableUserLocation()
        }
    }

    private fun createAndroidLocationProvider(context: Context): LocationProvider {
        val androidLocationProviderConfig = AndroidLocationProviderConfig(
            minTimeInterval = 250L.milliseconds,
            minDistance = Distance.meters(20.0)
        )
        return AndroidLocationProvider(
            context = context,
            config = androidLocationProviderConfig
        )
    }

    private fun enableUserLocation() {
        if (::tomTomMap.isInitialized) {

            tomTomMap.setLocationProvider(locationProvider)
            locationProvider.enable()

            val userLocation = locationProvider.lastKnownLocation

            if (userLocation != null) {
                val userGeoPoint = GeoPoint(userLocation.position.latitude, userLocation.position.longitude)
                Log.d("MainActivity", "User Location: $userGeoPoint")


                val newCameraOptions = CameraOptions(
                    position = userGeoPoint,
                    zoom = 16.0,
                    tilt = 45.0,
                    rotation = 90.0
                )

                val locationMarkerOptions = LocationMarkerOptions(
                    type = LocationMarkerOptions.Type.Chevron
                )

                tomTomMap.animateCamera(newCameraOptions, 2.seconds)
                tomTomMap.enableLocationMarker(locationMarkerOptions)

                createRoute(userGeoPoint)
            }
        }
    }

    private fun createRoute(userGeoPoint: GeoPoint) {
        val routePlanner = OnlineRoutePlanner.create(requireContext(), apiKey)
        Log.d("RoutePlanningOptions", "latitudeLocation: ${userGeoPoint.latitude}")
        Log.d("RoutePlanningOptions", "longitueLocation: ${userGeoPoint.longitude}")

        val pointA = GeoPoint(userGeoPoint.latitude, userGeoPoint.longitude)
        val pointB = GeoPoint(userGeoPoint.latitude + 2, userGeoPoint.longitude + 2)
        val routePlanningOptions = RoutePlanningOptions(
            itinerary = Itinerary(origin = pointA, destination = pointB),
            costModel = CostModel(routeType = RouteType.Efficient),
            vehicle = Vehicle.Pedestrian()
        )


        routePlanner.planRoute(
            routePlanningOptions,
            object : RoutePlanningCallback {
                override fun onSuccess(result: RoutePlanningResponse) {
                    Log.d("RoutePlanningOptions", "Itinerary: ${routePlanningOptions.itinerary}")

                    val routeOptions = RouteOptions(
                        geometry = result.routes.flatMap { it.geometry },
                        color = Color.BLUE,
                        outlineWidth = 3.0,
                        widths = listOf(WidthByZoom(5.0)),
                        progress = Distance.meters(1000.0),
                        instructions = listOf(
                            Instruction(
                                routeOffset = Distance.meters(1000.0),
                                combineWithNext = false
                            ),
                            Instruction(
                                routeOffset = Distance.meters(2000.0),
                                combineWithNext = true
                            ),
                            Instruction(routeOffset = Distance.meters(3000.0))
                        ),
                        tag = "Extra information about the route",
                        departureMarkerVisible = true,
                        destinationMarkerVisible = true
                    )
                    val route = tomTomMap.addRoute(routeOptions)
                }

                override fun onFailure(failure: RoutingFailure) {
                    Log.e("RoutePlanningOptions", "Route planning failed: $failure")
                }

                override fun onRoutePlanned(route: com.tomtom.sdk.routing.route.Route) {
                    Log.e("RoutePlanningOptions", "Route onRoutue:")

                }

            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(backToHomeListener: (() -> Unit)) =
            MapPageFragment().apply {
                this.backToHomeListener = backToHomeListener
            }
    }
}