package br.com.passenger.viewmodel

import androidx.navigation.NavController
import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.repository.MapRepository
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.mock.Mocks
import br.com.passenger.rules.MainCoroutineRule
import br.com.passenger.util.Resource
import br.com.passenger.view.route.RidesHistoryScreenRoute
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class RideOptionsViewModelUnitTest {
    private lateinit var rideRepository: RideRepository
    private lateinit var mapRepository: MapRepository
    private lateinit var viewModel: RideOptionsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        clearAllMocks(currentThreadOnly = true)
        rideRepository = mockk()
        mapRepository = mockk()
        viewModel = RideOptionsViewModel(rideRepository, mapRepository)
    }

    @Test
    fun `Teste estimateRide`() =
        runTest {
            val newRide = Mocks.getNewRide()
            val estimateRideResponse = Mocks.getEstimateRideResponse()
            val expected = Resource.Success(estimateRideResponse)
            coEvery { rideRepository.estimateRide(any()) } returns expected

            val result =
                viewModel.estimateRide(newRide.passengerId, newRide.origin, newRide.destination)

            expectThat(result).isEqualTo(expected)
        }

    @Test
    fun `Teste getMapUrl`() {
        val height = 100
        val width = 100
        val estimateRideResponse = Mocks.getEstimateRideResponse()
        val apiKey = "Test"
        val expected =
            "https://maps.googleapis.com/maps/api/staticmap?center=${estimateRideResponse.origin}&zoom=13&size=${width}x$height&markers=color:red%7Clabel:A%7C${estimateRideResponse.origin}&markers=color:green%7Clabel:B%7C${estimateRideResponse.destination}&key=$apiKey"
        every { mapRepository.getMap(any(), any(), any(), any()) } returns expected

        val result = viewModel.getMapUrl(height, width, estimateRideResponse)

        expectThat(result).isEqualTo(expected)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste confirmRide`() =
        runTest {
            val estimateRideResponse = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1
            val nav = mockk<NavController>(relaxed = true)
            val expected = Resource.Success(Mocks.getConfirmRideResponse())
            coEvery { rideRepository.confirmRide(any(), any(), any()) } returns expected

            viewModel.confirmRide(estimateRideResponse, passengerId, driverId, nav)
            advanceUntilIdle()

            expect {
                that(viewModel.isConfirmError.value).isFalse()
                that(viewModel.isConfirmLoading.value).isFalse()
            }
            verify(exactly = 1) { nav.navigate(RidesHistoryScreenRoute) }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste confirmRide com erro`() =
        runTest {
            val estimateRideResponse = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1
            val nav = mockk<NavController>(relaxed = true)
            val expected = Resource.Error<ConfirmRideResponse>("Error")
            coEvery { rideRepository.confirmRide(any(), any(), any()) } returns expected

            viewModel.confirmRide(estimateRideResponse, passengerId, driverId, nav)
            advanceUntilIdle()

            expect {
                that(viewModel.isConfirmError.value).isTrue()
                that(viewModel.isConfirmLoading.value).isFalse()
                that(viewModel.confirmErrorMessage.value).isEqualTo("Error")
            }
            verify(exactly = 0) { nav.navigate(RidesHistoryScreenRoute) }
        }
}
