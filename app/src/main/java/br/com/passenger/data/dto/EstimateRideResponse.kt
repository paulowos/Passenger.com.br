package br.com.passenger.data.dto

import br.com.passenger.model.Rider

data class EstimateRideResponse(
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val options: List<Option>,
    val origin: Origin,
    val routeResponse: RouteResponse,
) {
    data class Destination(
        val latitude: Double,
        val longitude: Double,
    )

    data class Option(
        val description: String,
        val id: Int,
        val name: String,
        val review: Review,
        val value: Double,
        val vehicle: String,
    ) {
        data class Review(
            val comment: String,
            val rating: Int,
        )
    }

    data class Origin(
        val latitude: Double,
        val longitude: Double,
    )

    data class RouteResponse(
        val geocodingResults: GeocodingResults,
        val routes: List<Route>,
    ) {
        data class GeocodingResults(
            val destination: Destination,
            val origin: Origin,
        ) {
            data class Destination(
                val geocoderStatus: GeocoderStatus,
                val placeId: String,
                val type: List<String>,
            ) {
                class GeocoderStatus
            }

            data class Origin(
                val geocoderStatus: GeocoderStatus,
                val placeId: String,
                val type: List<String>,
            ) {
                class GeocoderStatus
            }
        }

        data class Route(
            val description: String,
            val distanceMeters: Int,
            val duration: String,
            val legs: List<Leg>,
            val localizedValues: LocalizedValues,
            val polyline: Polyline,
            val polylineDetails: PolylineDetails,
            val routeLabels: List<String>,
            val staticDuration: String,
            val travelAdvisory: TravelAdvisory,
            val viewport: Viewport,
            val warnings: List<String>,
        ) {
            data class Leg(
                val distanceMeters: Int,
                val duration: String,
                val endLocation: EndLocation,
                val localizedValues: LocalizedValues,
                val polyline: Polyline,
                val startLocation: StartLocation,
                val staticDuration: String,
                val steps: List<Step>,
            ) {
                data class EndLocation(
                    val latLng: LatLng,
                ) {
                    data class LatLng(
                        val latitude: Double,
                        val longitude: Double,
                    )
                }

                data class LocalizedValues(
                    val distance: Distance,
                    val duration: Duration,
                    val staticDuration: StaticDuration,
                ) {
                    data class Distance(
                        val text: String,
                    )

                    data class Duration(
                        val text: String,
                    )

                    data class StaticDuration(
                        val text: String,
                    )
                }

                data class Polyline(
                    val encodedPolyline: String,
                )

                data class StartLocation(
                    val latLng: LatLng,
                ) {
                    data class LatLng(
                        val latitude: Double,
                        val longitude: Double,
                    )
                }

                data class Step(
                    val distanceMeters: Int,
                    val endLocation: EndLocation,
                    val localizedValues: LocalizedValues,
                    val navigationInstruction: NavigationInstruction,
                    val polyline: Polyline,
                    val startLocation: StartLocation,
                    val staticDuration: String,
                    val travelMode: String,
                ) {
                    data class EndLocation(
                        val latLng: LatLng,
                    ) {
                        data class LatLng(
                            val latitude: Double,
                            val longitude: Double,
                        )
                    }

                    data class LocalizedValues(
                        val distance: Distance,
                        val staticDuration: StaticDuration,
                    ) {
                        data class Distance(
                            val text: String,
                        )

                        data class StaticDuration(
                            val text: String,
                        )
                    }

                    data class NavigationInstruction(
                        val instructions: String,
                        val maneuver: String,
                    )

                    data class Polyline(
                        val encodedPolyline: String,
                    )

                    data class StartLocation(
                        val latLng: LatLng,
                    ) {
                        data class LatLng(
                            val latitude: Double,
                            val longitude: Double,
                        )
                    }
                }
            }

            data class LocalizedValues(
                val distance: Distance,
                val duration: Duration,
                val staticDuration: StaticDuration,
            ) {
                data class Distance(
                    val text: String,
                )

                data class Duration(
                    val text: String,
                )

                data class StaticDuration(
                    val text: String,
                )
            }

            data class Polyline(
                val encodedPolyline: String,
            )

            class PolylineDetails

            class TravelAdvisory

            data class Viewport(
                val high: High,
                val low: Low,
            ) {
                data class High(
                    val latitude: Double,
                    val longitude: Double,
                )

                data class Low(
                    val latitude: Double,
                    val longitude: Double,
                )
            }
        }
    }
}

fun EstimateRideResponse.Option.toRider() =
    Rider(
        description = description,
        name = name,
        price = value,
        review = review.rating,
        vehicle = vehicle,
    )
