package br.com.passenger.viewmodel

import androidx.navigation.NavController
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNull
import strikt.assertions.isTrue

class NewRideViewModelUnitTest {
    @Test
    fun `Teste valores inciais`() {
        val viewModel = NewRideViewModel()

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isEqualTo(null)
            that(viewModel.origin.value).isEqualTo(null)
            that(viewModel.destination.value).isEqualTo(null)
        }
    }

    @Test
    fun `Teste onPassengerIdChange`() {
        val viewModel = NewRideViewModel()
        val passengerId = "123"

        viewModel.onPassengerIdChange(passengerId)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isEqualTo(passengerId)
        }
    }

    @Test
    fun `Teste onPassengerIdChange vazio`() {
        val viewModel = NewRideViewModel()
        val passengerId = ""

        viewModel.onPassengerIdChange(passengerId)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isNull()
        }
    }

    @Test
    fun `Teste onOriginChange`() {
        val viewModel = NewRideViewModel()
        val origin = "123"

        viewModel.onOriginChange(origin)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.origin.value).isEqualTo(origin)
        }
    }

    @Test
    fun `Teste onOriginChange vazio`() {
        val viewModel = NewRideViewModel()
        val origin = ""

        viewModel.onOriginChange(origin)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.origin.value).isNull()
        }
    }

    @Test
    fun `Teste onDestinationChange`() {
        val viewModel = NewRideViewModel()
        val destination = "123"

        viewModel.onDestinationChange(destination)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.destination.value).isEqualTo(destination)
        }
    }

    @Test
    fun `Teste onDestinationChange vazio`() {
        val viewModel = NewRideViewModel()
        val destination = ""

        viewModel.onDestinationChange(destination)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.destination.value).isNull()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste onClick`() {
        val viewModel = NewRideViewModel()
        val nav = mockk<NavController>(relaxed = true)
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel.onClick(nav)

        expect {
            that(viewModel.isLoading.value).isTrue()
        }
    }
}
