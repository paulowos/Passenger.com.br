package br.com.passenger.mock

import br.com.passenger.data.dto.ErrorResponse
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.EstimateRideResponse.Destination
import br.com.passenger.data.dto.EstimateRideResponse.Origin
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse.Route
import br.com.passenger.data.dto.EstimateRideResponse.RouteResponse.Route.Leg
import br.com.passenger.model.NewRide

object Mocks {
    fun getEstimateRideResponse(): EstimateRideResponse =
        EstimateRideResponse(
            destination =
                Destination(
                    latitude = 0.0,
                    longitude = 0.0,
                ),
            distance = 1000,
            duration = 1000,
            options = emptyList(),
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

    fun getNewRide(): NewRide =
        NewRide(
            passengerId = "passengerId",
            origin = "origin",
            destination = "destination",
        )

    fun getErrorResponse(): ErrorResponse =
        ErrorResponse(
            errorDescription = "Http Error",
            errorCode = "400",
        )
}
