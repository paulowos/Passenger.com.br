package br.com.passenger.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.passenger.view.component.TextInput
import br.com.passenger.viewmodel.NewRideViewModel

@Composable
fun NewRideScreen(
    modifier: Modifier = Modifier,
    viewModel: NewRideViewModel = viewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Nova Corrida", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            hint = "ID do Passageiro",
            value = viewModel.passengerId.value,
            onValueChange = viewModel::onPassengerIdChange,
            isError = viewModel.isError.value,
        )
        TextInput(
            hint = "Endereço de Origem",
            value = viewModel.origin.value,
            onValueChange = viewModel::onOriginChange,
            isError = viewModel.isError.value,
        )
        TextInput(
            hint = "Endereço de Destino",
            value = viewModel.destination.value,
            onValueChange = viewModel::onDestinationChange,
            isError = viewModel.isError.value,
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.isError.value) {
            Text(
                text = viewModel.errorMessage.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(onClick = viewModel::onClick) {
            Text("Solicitar")
        }
    }
}
