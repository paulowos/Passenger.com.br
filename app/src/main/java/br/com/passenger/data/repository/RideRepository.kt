package br.com.passenger.data.repository

import br.com.passenger.data.dto.ErrorResponse
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.toEstimateRideRequest
import br.com.passenger.data.network.RideAPI
import br.com.passenger.model.NewRide
import br.com.passenger.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import javax.inject.Inject

@ViewModelScoped
class RideRepository
    @Inject
    constructor(
        private val rideAPI: RideAPI,
    ) {
        suspend fun estimateRide(newRide: NewRide): Resource<EstimateRideResponse> {
            try {
                val response = rideAPI.estimateRide(newRide.toEstimateRideRequest())
                return Resource.Success(response)
            } catch (e: HttpException) {
                val errorResponse = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                return Resource.Error(errorResponse.errorDescription)
            } catch (e: Exception) {
                return Resource.Error(e.message.toString())
            }
        }
    }
