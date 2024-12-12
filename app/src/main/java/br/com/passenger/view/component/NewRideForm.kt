package br.com.passenger.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.passenger.util.FieldNames
import br.com.passenger.viewmodel.NewRideViewModel

@Composable
fun NewRideForm(viewModel: NewRideViewModel = viewModel()) {
    val containsPassengerIdError = viewModel.fieldErrorNames.value.contains(FieldNames.PASSENGER_ID)
    val containsOriginError = viewModel.fieldErrorNames.value.contains(FieldNames.ORIGIN)
    val containsDestinationError = viewModel.fieldErrorNames.value.contains(FieldNames.DESTINATION)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextInput(
            hint = "ID do Passageiro",
            value = viewModel.passengerId.value,
            onValueChange = viewModel::onPassengerIdChange,
            icon = Icons.Sharp.Person,
            isError = viewModel.fieldErrorNames.value.contains(FieldNames.PASSENGER_ID),
        )
        if (containsPassengerIdError) {
            ErrorMessageInline(
                errorMessage = "ID do Passageiro é obrigatório",
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        TextInput(
            hint = "Endereço de Origem",
            value = viewModel.origin.value,
            onValueChange = viewModel::onOriginChange,
            icon = Icons.Sharp.Home,
            isError = containsOriginError,
        )
        if (containsOriginError) {
            ErrorMessageInline(
                errorMessage = "Endereço de Origem é obrigatório",
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        TextInput(
            hint = "Endereço de Destino",
            value = viewModel.destination.value,
            onValueChange = viewModel::onDestinationChange,
            icon = Icons.Sharp.Place,
            isError = containsDestinationError,
        )
        if (containsDestinationError) {
            ErrorMessageInline(
                errorMessage = "Endereço de Destino é obrigatório",
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
    }
}
