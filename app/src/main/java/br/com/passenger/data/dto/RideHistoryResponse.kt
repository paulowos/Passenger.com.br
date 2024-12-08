package br.com.passenger.data.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class RideHistoryResponse(
    @SerializedName("customer_id")
    val customerId: String,
    val rides: List<Ride>,
) {
    data class Ride(
        val date: LocalDate,
        val destination: String,
        val distance: Double,
        val driver: Driver,
        val duration: String,
        val id: Int,
        val origin: String,
        val value: Double,
    ) {
        data class Driver(
            val id: Int,
            val name: String,
        )
    }
}
