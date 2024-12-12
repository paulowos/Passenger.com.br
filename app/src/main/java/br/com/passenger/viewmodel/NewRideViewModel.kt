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
            fieldErrorNames.value.toMutableList().apply {
                remove(FieldNames.PASSENGER_ID)
                fieldErrorNames.value = this
            }
            this.passengerId.value = passengerId
        }

        fun onOriginChange(origin: String) {
            isLoading.value = false
            fieldErrorNames.value.toMutableList().apply {
                remove(FieldNames.ORIGIN)
                fieldErrorNames.value = this
            }
            this.origin.value = origin
        }

        fun onDestinationChange(destination: String) {
            isLoading.value = false
            fieldErrorNames.value.toMutableList().apply {
                remove(FieldNames.DESTINATION)
                fieldErrorNames.value = this
            }
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
                fieldErrorNames.value.toMutableList().apply {
                    add(FieldNames.PASSENGER_ID)
                    fieldErrorNames.value = this
                }
            }
            if (origin.value.isNullOrEmpty()) {
                fieldErrorNames.value.toMutableList().apply {
                    add(FieldNames.ORIGIN)
                    fieldErrorNames.value = this
                }
            }
            if (destination.value.isNullOrEmpty()) {
                fieldErrorNames.value.toMutableList().apply {
                    add(FieldNames.DESTINATION)
                    fieldErrorNames.value = this
                }
            }
            return fieldErrorNames.value.isEmpty()
        }
    }
