package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.model.NewRide
import br.com.passenger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewRideViewModel
    @Inject
    constructor(
        private val repository: RideRepository,
    ) : ViewModel() {
        val passengerId = mutableStateOf(null as String?)
        val origin = mutableStateOf(null as String?)
        val destination = mutableStateOf(null as String?)
        val isError = mutableStateOf(false)
        val errorMessage = mutableStateOf("")
        val isLoading = mutableStateOf(false)
        private var submitJob: Job? = null

        fun onPassengerIdChange(passengerId: String) {
            isError.value = false
            if (passengerId.isEmpty()) {
                this.passengerId.value = null
                return
            }
            this.passengerId.value = passengerId
        }

        fun onOriginChange(origin: String) {
            isError.value = false
            if (origin.isEmpty()) {
                this.origin.value = null
                return
            }
            this.origin.value = origin
        }

        fun onDestinationChange(destination: String) {
            isError.value = false
            if (destination.isEmpty()) {
                this.destination.value = null
                return
            }
            this.destination.value = destination
        }

        fun onClick() {
            isError.value = false
            isLoading.value = true
            submitJob?.cancel()
            submitJob =
                viewModelScope.launch {
                    delay(500)
                    val newRide =
                        NewRide(
                            passengerId = passengerId.value,
                            origin = origin.value,
                            destination = destination.value,
                        )
                    when (val response = repository.estimateRide(newRide)) {
                        is Resource.Success -> {
                            if (currentCoroutineContext().isActive) {
                                isError.value = false
                                isLoading.value = false
                            }
                        }
                        is Resource.Error -> {
                            if (currentCoroutineContext().isActive) {
                                errorMessage.value = response.message.toString()
                                isError.value = true
                                isLoading.value = false
                            }
                        }
                    }
                }
        }
    }
