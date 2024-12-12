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
        val fieldErrorNames = mutableStateOf(emptyList<FieldNames>())

        private var job: Job? = null

        fun onPassengerIdChange(passengerId: String) {
            isLoading.value = false
            fieldErrorNames.value -= FieldNames.PASSENGER_ID
            this.passengerId.value = passengerId
        }

        fun onOriginChange(origin: String) {
            isLoading.value = false
            fieldErrorNames.value -= FieldNames.ORIGIN
            this.origin.value = origin
        }

        fun onDestinationChange(destination: String) {
            isLoading.value = false
            fieldErrorNames.value -= FieldNames.DESTINATION
            this.destination.value = destination
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
                fieldErrorNames.value += FieldNames.PASSENGER_ID
            }
            if (origin.value.isNullOrEmpty()) {
                fieldErrorNames.value += FieldNames.ORIGIN
            }
            if (destination.value.isNullOrEmpty()) {
                fieldErrorNames.value += FieldNames.DESTINATION
            }
            return fieldErrorNames.value.isEmpty()
        }
    }
