package br.com.passenger.data.dto

import com.google.gson.annotations.SerializedName

data class ConfirmRideRequest(
    @SerializedName("customer_id")
    val customerId: String,
    val destination: String,
    val distance: Long,
    val driver: Driver,
    val duration: String,
    val origin: String,
    val value: Double,
) {
    data class Driver(
        val id: String,
        val name: String,
    )
}
