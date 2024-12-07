package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.view.route.RideOptionsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
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

        fun onPassengerIdChange(passengerId: String) {
            if (passengerId.isEmpty()) {
                this.passengerId.value = null
            } else {
                this.passengerId.value = passengerId
            }
        }

        fun onOriginChange(origin: String) {
            if (origin.isEmpty()) {
                this.origin.value = null
            } else {
                this.origin.value = origin
            }
        }

        fun onDestinationChange(destination: String) {
            if (destination.isEmpty()) {
                this.destination.value = null
            } else {
                this.destination.value = destination
            }
        }

        fun onClick(nav: NavController) {
            nav.navigate(
                RideOptionsScreenRoute(
                    passengerId = passengerId.value,
                    origin = origin.value,
                    destination = destination.value,
                ),
            )
        }
    }
