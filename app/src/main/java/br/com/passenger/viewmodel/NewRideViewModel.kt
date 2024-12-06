package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewRideViewModel
    @Inject
    constructor() : ViewModel() {
        val passengerId = mutableStateOf("")
        val origin = mutableStateOf("")
        val destination = mutableStateOf("")

        fun onPassengerIdChange(passengerId: String) {
            this.passengerId.value = passengerId
        }

        fun onOriginChange(origin: String) {
            this.origin.value = origin
        }

        fun onDestinationChange(destination: String) {
            this.destination.value = destination
        }

        fun onClick() {
        }
    }
