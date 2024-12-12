package br.com.passenger.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.passenger.util.FieldNames
import br.com.passenger.viewmodel.NewRideViewModel

@Composable
fun NewRideForm(viewModel: NewRideViewModel = viewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextInput(
            hint = "ID do Passageiro",
            value = viewModel.passengerId.value,
            onValueChange = viewModel::onPassengerIdChange,
            icon = Icons.Sharp.Person,
            isError = viewModel.fieldErrorName.value == FieldNames.PASSENGER_ID,
        )
        if (viewModel.fieldErrorName.value == FieldNames.PASSENGER_ID) {
            TextFieldErrorMessage(
                errorMessage = viewModel.fieldErrorMessage.value,
            )
        }
        TextInput(
            hint = "Endereço de Origem",
            value = viewModel.origin.value,
            onValueChange = viewModel::onOriginChange,
            icon = Icons.Sharp.Home,
            isError = viewModel.fieldErrorName.value == FieldNames.ORIGIN,
        )
        if (viewModel.fieldErrorName.value == FieldNames.ORIGIN) {
            TextFieldErrorMessage(
                errorMessage = viewModel.fieldErrorMessage.value,
            )
        }
        TextInput(
            hint = "Endereço de Destino",
            value = viewModel.destination.value,
            onValueChange = viewModel::onDestinationChange,
            icon = Icons.Sharp.Place,
            isError = viewModel.fieldErrorName.value == FieldNames.DESTINATION,
        )
        if (viewModel.fieldErrorName.value == FieldNames.DESTINATION) {
            TextFieldErrorMessage(
                errorMessage = viewModel.fieldErrorMessage.value,
            )
        }
    }
}
