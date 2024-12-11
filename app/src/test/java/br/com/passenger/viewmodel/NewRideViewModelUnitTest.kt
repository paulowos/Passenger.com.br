package br.com.passenger.viewmodel

import androidx.navigation.NavController
import br.com.passenger.rules.MainCoroutineRule
import br.com.passenger.view.route.RideOptionsScreenRoute
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
import strikt.assertions.isNull

class NewRideViewModelUnitTest {
    private lateinit var viewModel: NewRideViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = NewRideViewModel()
    }

    @Test
    fun `Teste valores inciais`() {
        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isEqualTo(null)
            that(viewModel.origin.value).isEqualTo(null)
            that(viewModel.destination.value).isEqualTo(null)
        }
    }

    @Test
    fun `Teste onPassengerIdChange`() {
        val passengerId = "123"

        viewModel.onPassengerIdChange(passengerId)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isEqualTo(passengerId)
        }
    }

    @Test
    fun `Teste onPassengerIdChange vazio`() {
        val passengerId = ""

        viewModel.onPassengerIdChange(passengerId)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isNull()
        }
    }

    @Test
    fun `Teste onOriginChange`() {
        val origin = "123"

        viewModel.onOriginChange(origin)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.origin.value).isEqualTo(origin)
        }
    }

    @Test
    fun `Teste onOriginChange vazio`() {
        val origin = ""

        viewModel.onOriginChange(origin)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.origin.value).isNull()
        }
    }

    @Test
    fun `Teste onDestinationChange`() {
        val destination = "123"

        viewModel.onDestinationChange(destination)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.destination.value).isEqualTo(destination)
        }
    }

    @Test
    fun `Teste onDestinationChange vazio`() {
        val destination = ""

        viewModel.onDestinationChange(destination)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.destination.value).isNull()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste onClick`() =
        runTest {
            val nav = mockk<NavController>(relaxed = true)

            viewModel.onClick(nav)
            advanceUntilIdle()

            expectThat(viewModel.isLoading.value).isFalse()
            verify { nav.navigate<RideOptionsScreenRoute>(any()) }
        }
}
