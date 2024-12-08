package br.com.passenger.data.network

import br.com.passenger.data.dto.ConfirmRideRequest
import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.dto.EstimateRideRequest
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.RideHistoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RideAPI {
    @POST("ride/estimate")
    suspend fun estimateRide(
        @Body rideRequest: EstimateRideRequest,
    ): EstimateRideResponse

    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body confirmRideRequest: ConfirmRideRequest,
    ): ConfirmRideResponse

    @GET("ride/{customerId}")
    suspend fun getRidesHistory(
        @Path("customerId") customerId: String,
        @Query("driver_id") driverId: String?,
    ): RideHistoryResponse
}
