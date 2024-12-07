package br.com.passenger.view.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.passenger.viewmodel.NewRideViewModel

@Composable
fun NewRideForm(
    modifier: Modifier = Modifier,
    viewModel: NewRideViewModel = viewModel(),
) {
    TextInput(
        hint = "ID do Passageiro",
        value = viewModel.passengerId.value,
        onValueChange = viewModel::onPassengerIdChange,
    )
    TextInput(
        hint = "Endereço de Origem",
        value = viewModel.origin.value,
        onValueChange = viewModel::onOriginChange,
    )
    TextInput(
        hint = "Endereço de Destino",
        value = viewModel.destination.value,
        onValueChange = viewModel::onDestinationChange,
    )
}
