package br.com.passenger.view.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.toRider
import br.com.passenger.util.Resource
import br.com.passenger.view.component.InfoContainer
import br.com.passenger.view.component.RiderCard
import br.com.passenger.viewmodel.RideOptionsViewModel
import kotlinx.coroutines.delay

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
            delay(2000)
            value = viewModel.estimateRide(passengerId, origin, destination)
        }
    if (rideEstimates.value is Resource.Loading) {
        InfoContainer()
        return
    }
    if (rideEstimates.value is Resource.Error) {
        val error = rideEstimates.value as Resource.Error
        InfoContainer(errorMessage = error.message!!, nav = nav)
        return
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        items(rideEstimates.value.data!!.options) {
            RiderCard(rider = it.toRider())
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
