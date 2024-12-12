package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.passenger.data.dto.toRideHistory
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.model.Driver
import br.com.passenger.model.RideHistory
import br.com.passenger.util.FieldNames
import br.com.passenger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RidesHistoryViewModel
    @Inject
    constructor(
        private val repository: RideRepository,
    ) : ViewModel() {
        val isExpanded = mutableStateOf(false)
        val drivers =
            mutableStateOf(repository.drivers + Driver(0, "Todos", 0))
        val selectedDriver = mutableStateOf("Selecione o motorista")
        val passengerId = mutableStateOf("")
        val ridesHistory = mutableStateOf(emptyList<RideHistory>())
        val isLoading = mutableStateOf(false)
        val isError = mutableStateOf(false)
        val errorMessage = mutableStateOf("")
        val fieldErrorNames = mutableStateOf(emptyList<FieldNames>())
        private var job: Job? = null

        fun toggleExpanded() {
            fieldErrorNames.value -= FieldNames.DRIVER
            isExpanded.value = !isExpanded.value
        }

        fun selectDriver(driverId: Int) {
            selectedDriver.value = driverId.toString()
            isExpanded.value = false
        }

        fun onPassengerIdChange(passengerId: String) {
            fieldErrorNames.value -= FieldNames.PASSENGER_ID
            this.passengerId.value = passengerId
        }

        fun getRidesHistory() {
            if (!validateFields()) return

            isLoading.value = true
            isError.value = false

            job?.cancel()
            job =
                viewModelScope.launch {
                    delay(500)
                    if (!validateFields()) {
                        isLoading.value = false
                        return@launch
                    }
                    val response =
                        if (selectedDriver.value == "0") {
                            repository.getRidesHistory(passengerId.value, null)
                        } else {
                            repository.getRidesHistory(passengerId.value, selectedDriver.value)
                        }

                    if (response is Resource.Success) {
                        ridesHistory.value = response.data!!.rides.map { it.toRideHistory() }
                        isLoading.value = false
                    } else {
                        isError.value = true
                        isLoading.value = false
                        errorMessage.value = response.message!!
                    }
                }
        }

        private fun validateFields(): Boolean {
            if (passengerId.value.isEmpty()) {
                fieldErrorNames.value += FieldNames.PASSENGER_ID
            }
            if (selectedDriver.value == "Selecione o motorista") {
                fieldErrorNames.value += FieldNames.DRIVER
            }
            return fieldErrorNames.value.isEmpty()
        }
    }
