package br.com.passenger.view.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.util.Resource
import br.com.passenger.view.component.InfoContainer
import br.com.passenger.viewmodel.RideOptionsViewModel

@Composable
fun RideOptionsScreen(
    passengerId: String?,
    origin: String?,
    destination: String?,
    modifier: Modifier = Modifier,
    viewModel: RideOptionsViewModel = hiltViewModel(),
    nav: NavController,
) {
    val rideEstimates =
        produceState<Resource<EstimateRideResponse>>(initialValue = Resource.Loading()) {
            value = viewModel.estimateRide(passengerId, origin, destination)
        }
    if (rideEstimates.value is Resource.Loading) {
        InfoContainer(isLoading = true, nav = nav)
        return
    }
    if (rideEstimates.value is Resource.Error) {
        val error = rideEstimates.value as Resource.Error
        InfoContainer(isError = true, errorMessage = error.message!!, nav = nav)
        return
    }
    Text("Ride Options Screen ${rideEstimates.value.data!!.distance} $origin $destination", modifier = modifier)
}
