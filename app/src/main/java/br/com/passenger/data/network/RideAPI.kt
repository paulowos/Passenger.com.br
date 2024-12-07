package br.com.passenger.data.network

import br.com.passenger.data.dto.ConfirmRideRequest
import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.dto.EstimateRideRequest
import br.com.passenger.data.dto.EstimateRideResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface RideAPI {
    @POST("ride/estimate")
    suspend fun estimateRide(
        @Body rideRequest: EstimateRideRequest,
    ): EstimateRideResponse

    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body confirmRideRequest: ConfirmRideRequest,
    ): ConfirmRideResponse
}
