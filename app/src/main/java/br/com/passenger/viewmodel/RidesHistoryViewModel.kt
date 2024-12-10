package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.passenger.data.dto.toRideHistory
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.model.RideHistory
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
        val drivers = mutableStateOf(listOf("Todos"))
        val selectedDriver = mutableStateOf("Selecione o motorista")
        val passengerId = mutableStateOf("")
        val ridesHistory = mutableStateOf(emptyList<RideHistory>())
        val isLoading = mutableStateOf(false)
        val isError = mutableStateOf(false)
        val errorMessage = mutableStateOf("")
        private var job: Job? = null

        fun toggleExpanded() {
            isExpanded.value = !isExpanded.value
        }

        fun selectDriver(driver: String) {
            selectedDriver.value = driver
            isExpanded.value = false
        }

        fun onPassengerIdChange(passengerId: String) {
            this.passengerId.value = passengerId
        }

        fun getRidesHistory() {
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
                        if (selectedDriver.value == "Todos") {
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
                errorMessage.value = "O campo ID do Passageiro é obrigatório"
                isError.value = true
                return false
            }
            return true
        }
    }
