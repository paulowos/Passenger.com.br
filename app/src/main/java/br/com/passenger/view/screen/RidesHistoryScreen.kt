package br.com.passenger.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.passenger.util.FieldNames
import br.com.passenger.view.component.DriverDropdownMenu
import br.com.passenger.view.component.ErrorMessageInline
import br.com.passenger.view.component.InfoContainer
import br.com.passenger.view.component.PrimaryButton
import br.com.passenger.view.component.RideHistoryCard
import br.com.passenger.view.component.ScreenTitle
import br.com.passenger.view.component.TextInput
import br.com.passenger.viewmodel.RidesHistoryViewModel

@Composable
fun RidesHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RidesHistoryViewModel = hiltViewModel(),
) {
    val containsPassengerIdError = viewModel.fieldErrorNames.value.contains(FieldNames.PASSENGER_ID)

    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ScreenTitle("Histórico de Corridas")
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            modifier = modifier,
            hint = "ID do Passageiro",
            value = viewModel.passengerId.value,
            onValueChange = viewModel::onPassengerIdChange,
            icon = Icons.Sharp.Person,
            isError = containsPassengerIdError,
        )
        if (containsPassengerIdError) {
            ErrorMessageInline(
                errorMessage = "ID do Passageiro é obrigatório",
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        DriverDropdownMenu()
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            text = "Buscar",
            modifier = Modifier.fillMaxWidth(),
            onClick = viewModel::getRidesHistory,
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.isLoading.value) {
            InfoContainer()
            return
        }
        if (viewModel.isError.value) {
            InfoContainer(
                errorMessage = viewModel.errorMessage.value,
            )
            return
        }
        LazyColumn {
            items(viewModel.ridesHistory.value) { ride ->
                RideHistoryCard(ride = ride)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
