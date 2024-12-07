package br.com.passenger.view.route

import kotlinx.serialization.Serializable

@Serializable
data class RideOptionsScreenRoute(
    val passengerId: String?,
    val origin: String?,
    val destination: String?,
)
