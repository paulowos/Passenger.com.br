package br.com.passenger.model

data class RideHistory(
    val date: String,
    val destination: String,
    val distance: String,
    val driver: Driver,
    val duration: String,
    val id: Int,
    val origin: String,
    val value: String,
) {
    data class Driver(
        val id: Int,
        val name: String,
    )
}
