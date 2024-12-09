package br.com.passenger.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
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
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model =
            viewModel.getMapUrl(
                height = width / 2,
                width = width,
                rideResponse = rideEstimates.data!!,
            ),
        contentDescription = "Mapa",
    )
    Spacer(modifier = Modifier.height(16.dp))
}
