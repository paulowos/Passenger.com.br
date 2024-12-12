package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.passenger.util.FieldNames
import br.com.passenger.view.route.RideOptionsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewRideViewModel
    @Inject
    constructor() : ViewModel() {
        val passengerId = mutableStateOf(null as String?)
        val origin = mutableStateOf(null as String?)
        val destination = mutableStateOf(null as String?)
        val isLoading = mutableStateOf(false)
        val fieldErrorName = mutableStateOf(null as FieldNames?)
        val fieldErrorMessage = mutableStateOf("")
        private var job: Job? = null

        fun onPassengerIdChange(passengerId: String) {
            isLoading.value = false
            if (passengerId.isEmpty()) {
                this.passengerId.value = null
            } else {
                this.passengerId.value = passengerId
            }
        }

        fun onOriginChange(origin: String) {
            isLoading.value = false
            if (origin.isEmpty()) {
                this.origin.value = null
            } else {
                this.origin.value = origin
            }
        }

        fun onDestinationChange(destination: String) {
            isLoading.value = false
            if (destination.isEmpty()) {
                this.destination.value = null
            } else {
                this.destination.value = destination
            }
        }

        fun onClick(nav: NavController) {
            if (!validateFields()) return
            isLoading.value = true
            job?.cancel()
            job =
                viewModelScope.launch {
                    delay(500)
                    nav.navigate(
                        RideOptionsScreenRoute(
                            passengerId = passengerId.value,
                            origin = origin.value,
                            destination = destination.value,
                        ),
                    )
                    isLoading.value = false
                }
        }

        private fun validateFields(): Boolean {
            if (passengerId.value.isNullOrEmpty()) {
                fieldErrorName.value = FieldNames.PASSENGER_ID
                fieldErrorMessage.value = "ID do Passageiro é obrigatório"
                return false
            }
            if (origin.value.isNullOrEmpty()) {
                fieldErrorName.value = FieldNames.ORIGIN
                fieldErrorMessage.value = "Endereço de Origem é obrigatório"
                return false
            }
            if (destination.value.isNullOrEmpty()) {
                fieldErrorName.value = FieldNames.DESTINATION
                fieldErrorMessage.value = "Endereço de Destino é obrigatório"
                return false
            }
            return true
        }
    }
