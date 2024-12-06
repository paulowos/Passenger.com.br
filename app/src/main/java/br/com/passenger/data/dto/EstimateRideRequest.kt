package br.com.passenger.data.dto

import br.com.passenger.model.NewRide
import com.google.gson.annotations.SerializedName

data class EstimateRideRequest(
    @SerializedName("customer_id")
    val customerId: String?,
    val destination: String?,
    val origin: String?,
)

fun NewRide.toEstimateRideRequest() =
    EstimateRideRequest(
        customerId = passengerId,
        destination = destination,
        origin = origin,
    )
