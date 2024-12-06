package br.com.passenger.data.dto


import com.google.gson.annotations.SerializedName

data class EstimateRideRequest(
    @SerializedName("customer_id")
    val customerId: String,
    val destination: String,
    val origin: String
)