package br.com.passenger.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.passenger.view.component.NewRideForm
import br.com.passenger.view.component.PrimaryButton
import br.com.passenger.viewmodel.NewRideViewModel

@Composable
fun NewRideScreen(
    nav: NavController,
    modifier: Modifier = Modifier,
    viewModel: NewRideViewModel = hiltViewModel(),
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
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(16.dp))
        NewRideForm()
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton("Solicitar") {
            viewModel.onClick(nav)
        }
    }
}
