package br.com.passenger.data.network

import br.com.passenger.data.dto.EstimateRideRequest
import br.com.passenger.data.dto.EstimateRideResponse
import retrofit2.http.POST

interface RideAPI {
    @POST("ride/estimate")
    suspend fun estimateRide(rideRequest: EstimateRideRequest): EstimateRideResponse
}
