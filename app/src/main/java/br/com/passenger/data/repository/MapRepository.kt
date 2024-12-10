package br.com.passenger.data.repository

import br.com.passenger.BuildConfig
import br.com.passenger.data.dto.EstimateRideResponse
import dagger.hilt.android.scopes.ViewModelScoped
import java.net.URLEncoder

@ViewModelScoped
class MapRepository {
    fun getMap(
        height: Int,
        width: Int,
        rideResponse: EstimateRideResponse,
        mapsApiKey: String = BuildConfig.MAPS_API_KEY,
    ): String {
        val size = "${width}x$height"

        val polyline =
            URLEncoder.encode(
                rideResponse.routeResponse.routes[0]
                    .legs[0]
                    .polyline.encodedPolyline,
                "UTF-8",
            )
        val path =
            "color:0x0000ffff|enc:$polyline"

        val markersOrigin =
            "color:green|label:O|${rideResponse.origin.latitude},${rideResponse.origin.longitude}"
        val markersDestination =
            "color:red|label:D|${rideResponse.destination.latitude},${rideResponse.destination.longitude}"

        val language = "pt-BR"

        val imgUrl =
            "${BuildConfig.MAPS_API_URL}staticmap?size=$size&path=$path&key=$mapsApiKey&scale=2&markers=$markersOrigin&markers=$markersDestination&language=$language"

        return imgUrl
    }
}
