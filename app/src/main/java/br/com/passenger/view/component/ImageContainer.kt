package br.com.passenger.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.util.Resource
import br.com.passenger.viewmodel.RideOptionsViewModel
import coil3.compose.AsyncImage

@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    viewModel: RideOptionsViewModel = hiltViewModel(),
    rideEstimates: Resource<EstimateRideResponse>,
) {
    val width = LocalConfiguration.current.screenWidthDp
    val isLoading = remember { mutableStateOf(true) }
    val isError = remember { mutableStateOf(false) }
    val errorMessage = "Erro ao carregar o mapa"

    Spacer(modifier = Modifier.height(8.dp))

    if (isLoading.value) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primaryContainer)
    }

    AsyncImage(
        modifier = modifier.fillMaxWidth().clip(MaterialTheme.shapes.medium),
        model =
            viewModel.getMapUrl(
                height = width / 2,
                width = width,
                rideResponse = rideEstimates.data!!,
            ),
        contentDescription = "Mapa",
        onSuccess = {
            isLoading.value = false
        },
        onError = {
            isError.value = true
            isLoading.value = false
        },
    )

    if (isError.value) {
        Text(
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}
