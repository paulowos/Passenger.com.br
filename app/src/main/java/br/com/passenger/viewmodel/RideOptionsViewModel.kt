package br.com.passenger.viewmodel

import androidx.lifecycle.ViewModel
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.model.NewRide
import br.com.passenger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RideOptionsViewModel
    @Inject
    constructor(
        private val repository: RideRepository,
    ) : ViewModel() {
        suspend fun estimateRide(
            passengerId: String?,
            origin: String?,
            destination: String?,
        ): Resource<EstimateRideResponse> {
            val newRide =
                NewRide(
                    passengerId = passengerId,
                    origin = origin,
                    destination = destination,
                )
            return repository.estimateRide(newRide)
        }
    }
