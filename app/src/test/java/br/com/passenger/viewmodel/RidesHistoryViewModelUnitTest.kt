package br.com.passenger.viewmodel

import br.com.passenger.data.dto.toRideHistory
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.mock.Mocks
import br.com.passenger.rules.MainCoroutineRule
import br.com.passenger.util.Resource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class RidesHistoryViewModelUnitTest {
    private lateinit var viewModel: RidesHistoryViewModel
    private lateinit var repository: RideRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @Before
    fun setup() {
        clearAllMocks(currentThreadOnly = true)
        repository = mockk()
        viewModel = RidesHistoryViewModel(repository)
    }

    @Test
    fun `Teste valores iniciais`() {
        expect {
            that(viewModel.isExpanded.value).isFalse()
            that(viewModel.drivers.value).isEqualTo(listOf("Todos"))
            that(viewModel.selectedDriver.value).isEqualTo("Selecione o motorista")
            that(viewModel.passengerId.value).isEqualTo("")
            that(viewModel.ridesHistory.value).isEmpty()
            that(viewModel.isLoading.value).isFalse()
            that(viewModel.isError.value).isFalse()
            that(viewModel.errorMessage.value).isEqualTo("")
        }
    }

    @Test
    fun `Teste toggleExpanded`() {
        viewModel.toggleExpanded()
        expectThat(viewModel.isExpanded.value).isTrue()
    }

    @Test
    fun `Teste selectDriver`() {
        viewModel.selectDriver("Motorista 1")
        expectThat(viewModel.selectedDriver.value).isEqualTo("Motorista 1")
        expectThat(viewModel.isExpanded.value).isFalse()
    }

    @Test
    fun `Teste onPassengerIdChange`() {
        viewModel.onPassengerIdChange("123")
        expectThat(viewModel.passengerId.value).isEqualTo("123")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste getRidesHistory sucesso`() =
        runTest {
            val expected = Mocks.getRideHistoryResponse()
            viewModel.passengerId.value = "123"
            viewModel.selectedDriver.value = "Todos"
            coEvery { repository.getRidesHistory(any(), any()) } returns Resource.Success(expected)

            viewModel.getRidesHistory()
            advanceUntilIdle()

            expect {
                that(viewModel.isLoading.value).isFalse()
                that(viewModel.isError.value).isFalse()
                that(viewModel.ridesHistory.value).isEqualTo(expected.rides.map { it.toRideHistory() })
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste getRidesHistory com campos invalidos`() =
        runTest {
            viewModel.getRidesHistory()
            advanceUntilIdle()

            expect {
                that(viewModel.isLoading.value).isFalse()
                that(viewModel.isError.value).isTrue()
                that(viewModel.errorMessage.value).isEqualTo("O campo ID do Passageiro é obrigatório")
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste getRidesHistory falha`() =
        runTest {
            viewModel.passengerId.value = "123"
            viewModel.selectedDriver.value = "Todos"
            coEvery { repository.getRidesHistory(any(), any()) } returns Resource.Error("Error")

            viewModel.getRidesHistory()
            advanceUntilIdle()

            expect {
                that(viewModel.isLoading.value).isFalse()
                that(viewModel.isError.value).isTrue()
                that(viewModel.errorMessage.value).isEqualTo("Error")
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste getRidesHistory sucesso com driver selecionado`() =
        runTest {
            val expected = Mocks.getRideHistoryResponse()
            viewModel.passengerId.value = "123"
            viewModel.selectedDriver.value = "Motorista 1"
            coEvery { repository.getRidesHistory(any(), any()) } returns Resource.Success(expected)

            viewModel.getRidesHistory()
            advanceUntilIdle()

            coVerify { repository.getRidesHistory("123", "Motorista 1") }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Teste getRidesHistory sucesso com opcao Todos selecionada`() =
        runTest {
            val expected = Mocks.getRideHistoryResponse()
            viewModel.passengerId.value = "123"
            viewModel.selectedDriver.value = "Todos"
            coEvery { repository.getRidesHistory(any(), any()) } returns Resource.Success(expected)

            viewModel.getRidesHistory()
            advanceUntilIdle()

            coVerify { repository.getRidesHistory("123", null) }
        }
}
