package br.com.passenger.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import br.com.passenger.view.component.NewRideForm
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
        Text(
            text = "Nova Corrida",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        NewRideForm()
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.isError.value && !viewModel.isLoading.value) {
            Text(
                text = viewModel.errorMessage.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(onClick = viewModel::onClick) {
            Text("Solicitar")
        }
    }
}
