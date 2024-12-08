package br.com.passenger.model

data class RideHistory(
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
