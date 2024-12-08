package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.model.NewRide
import br.com.passenger.util.Resource
import br.com.passenger.view.route.RidesHistoryScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideOptionsViewModel
    @Inject
    constructor(
        private val repository: RideRepository,
    ) : ViewModel() {
        val isConfirmError = mutableStateOf(false)
        val confirmErrorMessage = mutableStateOf("")
        val isConfirmLoading = mutableStateOf(false)
        val driverId = mutableIntStateOf(0)

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

        fun confirmRide(
            ride: EstimateRideResponse,
            passengerId: String,
            driverId: Int,
            nav: NavController,
        ) {
            this.driverId.intValue = driverId
            isConfirmError.value = false
            isConfirmLoading.value = true
            viewModelScope.launch {
                delay(500)
                val result = repository.confirmRide(ride, passengerId, driverId)
                if (result is Resource.Error) {
                    isConfirmError.value = true
                    isConfirmLoading.value = false
                    confirmErrorMessage.value = result.message!!
                    return@launch
                }
                isConfirmError.value = false
                isConfirmLoading.value = false
                nav.navigate(RidesHistoryScreenRoute)
            }
        }
    }
