package br.com.passenger.data.repository

import android.util.Log
import br.com.passenger.BuildConfig
import br.com.passenger.data.dto.EstimateRideResponse
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class MapRepository {
    fun getMap(
        height: Int,
        width: Int,
        rideResponse: EstimateRideResponse,
    ): String {
        val size = "${width}x$height"
        val key = BuildConfig.MAPS_API_KEY
        val route =
            rideResponse.routeResponse.routes[0]
                .legs[0]
                .steps
                .map {
                    "${it.startLocation.latLng.latitude},${it.startLocation.latLng.longitude}|${it.endLocation.latLng.latitude},${it.endLocation.latLng.longitude}"
                }.joinToString("|")

//        val path =
//            "color:0x0000ffff|weight=5|enc:${rideResponse.routeResponse.routes[0].legs[0].polyline.encodedPolyline}"
        val path = "color:0x0000ffff|weight=5|$route"

        val markersOrigin =
            "color:blue|label:O|${rideResponse.origin.latitude},${rideResponse.origin.longitude}"
        val markersDestination =
            "color:red|label:D|${rideResponse.destination.latitude},${rideResponse.destination.longitude}"

        val imgUrl =
            "${BuildConfig.MAPS_API_URL}staticmap?size=$size&path=$path&key=$key&scale=2&markers=$markersOrigin&markers=$markersDestination"

        Log.d("MapRepository", "getMap: $imgUrl")
        return imgUrl
    }
}
