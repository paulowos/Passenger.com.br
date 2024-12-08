package br.com.passenger.data.dto

import android.icu.text.NumberFormat
import br.com.passenger.model.RideHistory
import br.com.passenger.util.dateTimeFormatter
import com.google.gson.annotations.SerializedName

data class RideHistoryResponse(
    @SerializedName("customer_id")
    val customerId: String,
    val rides: List<Ride>,
) {
    data class Ride(
        val date: String,
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

fun RideHistoryResponse.Ride.toRideHistory() =
    RideHistory(
        date = dateTimeFormatter(date),
        destination = destination,
        distance = distance.toTwoDecimalPlaces(),
        driver =
            RideHistory.Driver(
                id = driver.id,
                name = driver.name,
            ),
        duration = duration,
        id = id,
        origin = origin,
        value = value.toTwoDecimalPlaces(),
    )

fun Double.toTwoDecimalPlaces(): Double =
    NumberFormat
        .getInstance()
        .apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }.format(this)
        .toDouble()
