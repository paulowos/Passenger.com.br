package br.com.passenger.data.repository

import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.RideHistoryResponse
import br.com.passenger.data.dto.toConfirmRideRequest
import br.com.passenger.data.dto.toEstimateRideRequest
import br.com.passenger.data.network.RideAPI
import br.com.passenger.model.Driver
import br.com.passenger.model.NewRide
import br.com.passenger.util.Resource
import br.com.passenger.util.httpExceptionHandling
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@ViewModelScoped
class RideRepository
    @Inject
    constructor(
        private val rideAPI: RideAPI,
    ) {
        fun getDrivers() =
            listOf(
                Driver(1, "Homer Simpson", 1),
                Driver(2, "Dominic Toretto", 5),
                Driver(3, "James Bond", 10),
            )

        suspend fun estimateRide(newRide: NewRide): Resource<EstimateRideResponse> {
            try {
                val response = rideAPI.estimateRide(newRide.toEstimateRideRequest())
                return Resource.Success(response)
            } catch (e: HttpException) {
                val errorResponse = httpExceptionHandling(e)
                return Resource.Error(errorResponse.errorDescription)
            } catch (e: UnknownHostException) {
                return Resource.Error("Sem conexão com a internet")
            } catch (e: Exception) {
                return Resource.Error("Erro desconhecido")
            }
        }

        suspend fun confirmRide(
            ride: EstimateRideResponse,
            passengerId: String,
            driverId: Int,
        ): Resource<ConfirmRideResponse> {
            try {
                val response = rideAPI.confirmRide(ride.toConfirmRideRequest(passengerId, driverId))
                return Resource.Success(response)
            } catch (e: HttpException) {
                val errorResponse = httpExceptionHandling(e)
                return Resource.Error(errorResponse.errorDescription)
            } catch (e: UnknownHostException) {
                return Resource.Error("Sem conexão com a internet")
            } catch (e: Exception) {
                return Resource.Error("Erro desconhecido")
            }
        }

        suspend fun getRidesHistory(
            customerId: String,
            driverId: String?,
        ): Resource<RideHistoryResponse> {
            try {
                val response = rideAPI.getRidesHistory(customerId, driverId)
                return Resource.Success(response)
            } catch (e: HttpException) {
                val errorResponse = httpExceptionHandling(e)
                return Resource.Error(errorResponse.errorDescription)
            } catch (e: UnknownHostException) {
                return Resource.Error("Sem conexão com a internet")
            } catch (e: Exception) {
                return Resource.Error("Erro desconhecido")
            }
        }
    }
