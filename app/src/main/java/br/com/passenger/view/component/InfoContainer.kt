package br.com.passenger.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.passenger.view.route.NewRideScreenRoute

@Composable
fun InfoContainer(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primaryContainer)
    }
}

@Composable
fun InfoContainer(
    errorMessage: String,
    nav: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Icon(
            imageVector = Icons.Sharp.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier =
                Modifier
                    .size(60.dp)
                    .padding(bottom = 8.dp),
        )
        Text(
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Voltar") {
            nav.popBackStack<NewRideScreenRoute>(inclusive = false)
        }
    }
}

@Composable
fun InfoContainer(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Icon(
            imageVector = Icons.Sharp.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier =
                Modifier
                    .size(60.dp)
                    .padding(bottom = 8.dp),
        )
        Text(
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )
    }
}
