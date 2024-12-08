package br.com.passenger.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RidesHistoryViewModel
    @Inject
    constructor() : ViewModel() {
        val isExpanded = mutableStateOf(false)
        val drivers = mutableStateOf(listOf("Todos"))
        val selectedDriver = mutableStateOf("Selecione o motorista")

        fun toggleExpanded() {
            isExpanded.value = !isExpanded.value
        }

        fun selectDriver(driver: String) {
            selectedDriver.value = driver
            isExpanded.value = false
        }
    }
