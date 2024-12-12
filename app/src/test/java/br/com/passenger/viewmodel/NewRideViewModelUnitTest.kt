package br.com.passenger.viewmodel

import androidx.navigation.NavController
import br.com.passenger.rules.MainCoroutineRule
import br.com.passenger.util.FieldNames
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
import strikt.assertions.contains
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse

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
            that(viewModel.passengerId.value).isEmpty()
            that(viewModel.origin.value).isEmpty()
            that(viewModel.destination.value).isEmpty()
            that(viewModel.fieldErrorNames.value).isEqualTo(emptyList())
        }
    }

    @Test
    fun `Teste onPassengerIdChange`() {
        val passengerId = "123"

        viewModel.onPassengerIdChange(passengerId)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.passengerId.value).isEqualTo(passengerId)
            that(viewModel.fieldErrorNames.value).not().contains(FieldNames.PASSENGER_ID)
        }
    }

    @Test
    fun `Teste onOriginChange`() {
        val origin = "123"

        viewModel.onOriginChange(origin)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.origin.value).isEqualTo(origin)
            that(viewModel.fieldErrorNames.value).not().contains(FieldNames.ORIGIN)
        }
    }

    @Test
    fun `Teste onDestinationChange`() {
        val destination = "123"

        viewModel.onDestinationChange(destination)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.destination.value).isEqualTo(destination)
            that(viewModel.fieldErrorNames.value).not().contains(FieldNames.DESTINATION)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste onClick`() =
        runTest {
            val nav = mockk<NavController>(relaxed = true)
            viewModel.passengerId.value = "Teste"
            viewModel.origin.value = "Teste"
            viewModel.destination.value = "Teste"

            viewModel.onClick(nav)
            advanceUntilIdle()

            expectThat(viewModel.isLoading.value).isFalse()
            verify { nav.navigate<RideOptionsScreenRoute>(any()) }
        }

    @Test
    fun `Teste onClick com campos vazios`() {
        val nav = mockk<NavController>(relaxed = true)

        viewModel.onClick(nav)

        expect {
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.fieldErrorNames.value).contains(FieldNames.PASSENGER_ID)
            that(viewModel.fieldErrorNames.value).contains(FieldNames.ORIGIN)
            that(viewModel.fieldErrorNames.value).contains(FieldNames.DESTINATION)
        }
    }
}
