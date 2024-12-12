package br.com.passenger.mock

import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.dto.ErrorResponse
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.EstimateRideResponse.Destination
import br.com.passenger.data.dto.EstimateRideResponse.Origin
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse.Route
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse.Route.Leg
import br.com.passenger.data.dto.RideHistoryResponse
import br.com.passenger.model.Driver
import br.com.passenger.model.NewRide

object Mocks {
    fun getEstimateRideResponse(
        isOptionEmpty: Boolean = false,
        isDistanceInvalid: Boolean = false,
    ) = EstimateRideResponse(
        destination =
            Destination(
                latitude = 0.0,
                longitude = 0.0,
            ),
        distance = if (isDistanceInvalid) 0 else 1000,
        duration = 1000,
        options = if (isOptionEmpty) emptyList() else listOf(getEstimateRideResponseOption()),
        origin =
            Origin(
                latitude = 0.0,
                longitude = 0.0,
            ),
        routeResponse =
            RouteResponse(
                routes =
                    listOf(
                        Route(
                            legs =
                                listOf(
                                    Leg(
                                        polyline =
                                            Leg.Polyline(
                                                encodedPolyline = "encodedPolyline",
                                            ),
                                        distanceMeters = 1000,
                                        duration = "10:30",
                                        endLocation =
                                            Leg.EndLocation(
                                                latLng =
                                                    Leg.EndLocation.LatLng(
                                                        longitude = 0.0,
                                                        latitude = 0.0,
                                                    ),
                                            ),
                                        localizedValues =
                                            Leg.LocalizedValues(
                                                distance =
                                                    Leg.LocalizedValues.Distance(
                                                        text = "text",
                                                    ),
                                                duration =
                                                    Leg.LocalizedValues.Duration(
                                                        text = "text",
                                                    ),
                                                staticDuration =
                                                    Leg.LocalizedValues.StaticDuration(
                                                        text = "text",
                                                    ),
                                            ),
                                        startLocation =
                                            Leg.StartLocation(
                                                latLng =
                                                    Leg.StartLocation.LatLng(
                                                        longitude = 0.0,
                                                        latitude = 0.0,
                                                    ),
                                            ),
                                        staticDuration = "10:30",
                                        steps = emptyList(),
                                    ),
                                ),
                            description = "description",
                            distanceMeters = 1000,
                            duration = "10:30",
                            localizedValues =
                                Route.LocalizedValues(
                                    distance =
                                        Route.LocalizedValues.Distance(
                                            text = "text",
                                        ),
                                    duration =
                                        Route.LocalizedValues.Duration(
                                            text = "text",
                                        ),
                                    staticDuration =
                                        Route.LocalizedValues.StaticDuration(
                                            text = "text",
                                        ),
                                ),
                            polyline =
                                Route.Polyline(
                                    encodedPolyline = "encodedPolyline",
                                ),
                            polylineDetails = Route.PolylineDetails(),
                            routeLabels = listOf("routeLabels"),
                            staticDuration = "10:30",
                            travelAdvisory = Route.TravelAdvisory(),
                            viewport =
                                Route.Viewport(
                                    high = Route.Viewport.High(latitude = 1000.0, longitude = 1000.0),
                                    low =
                                        Route.Viewport.Low(
                                            latitude = 1000.0,
                                            longitude = 1000.0,
                                        ),
                                ),
                            warnings = listOf("warnings"),
                        ),
                    ),
                geocodingResults =
                    RouteResponse.GeocodingResults(
                        destination =
                            RouteResponse.GeocodingResults.Destination(
                                geocoderStatus = RouteResponse.GeocodingResults.Destination.GeocoderStatus(),
                                placeId = "placeId",
                                type = listOf("type"),
                            ),
                        origin =
                            RouteResponse.GeocodingResults.Origin(
                                geocoderStatus = RouteResponse.GeocodingResults.Origin.GeocoderStatus(),
                                placeId = "placeId",
                                type = listOf("type"),
                            ),
                    ),
            ),
    )

    fun getNewRide() =
        NewRide(
            passengerId = "passengerId",
            origin = "origin",
            destination = "destination",
        )

    fun getErrorResponse() =
        ErrorResponse(
            errorDescription = "Http Error",
            errorCode = "400",
        )

    fun getConfirmRideResponse() = ConfirmRideResponse(true)

    fun getRideHistoryResponse() =
        RideHistoryResponse(
            customerId = "customerId",
            rides = emptyList(),
        )

    fun getEstimateRideResponseOption() =
        EstimateRideResponse.Option(
            description = "description",
            id = 1,
            name = "name",
            review =
                EstimateRideResponse.Option.Review(
                    comment = "comment",
                    rating = 5,
                ),
            value = 10.0,
            vehicle = "vehicle",
        )

    fun getListOfDrivers() =
        listOf(
            Driver(1, "Motorista 1", minKm = 1),
            Driver(2, "Motorista 2", minKm = 5),
            Driver(3, "Motorista 3", minKm = 10),
        )
}
