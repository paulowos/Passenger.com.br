package br.com.passenger

import br.com.passenger.data.dto.ConfirmRideResponse
import br.com.passenger.data.dto.EstimateRideResponse
import br.com.passenger.data.dto.RideHistoryResponse
import br.com.passenger.data.network.RideAPI
import br.com.passenger.data.repository.RideRepository
import br.com.passenger.mock.Mocks
import br.com.passenger.util.Resource
import br.com.passenger.util.httpExceptionHandling
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import java.net.UnknownHostException

class RideRepositoryUnitTest {
    @Before
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `Teste estimateRide sucesso`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            val expected = Mocks.getEstimateRideResponse()
            coEvery { rideApi.estimateRide(any()) } returns expected

            val rideRepository = RideRepository(rideApi)
            val newRide = Mocks.getNewRide()
            val result = rideRepository.estimateRide(newRide)

            expect {
                that(result).isA<Resource.Success<EstimateRideResponse>>()
                that((result as Resource.Success<EstimateRideResponse>).data).isEqualTo(expected)
            }
        }

    @Test
    fun `Teste estimateRide Exception`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.estimateRide(any()) } throws Exception()

            val rideRepository = RideRepository(rideApi)
            val newRide = Mocks.getNewRide()
            val result = rideRepository.estimateRide(newRide)

            expect {
                that(result).isA<Resource.Error<EstimateRideResponse>>()
                that((result as Resource.Error<EstimateRideResponse>).message).isEqualTo("Erro desconhecido")
            }
        }

    @Test
    fun `Teste estimateRide HttpException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.estimateRide(any()) } throws
                HttpException(
                    Response<String>.error<String>(
                        400,
                        "Http Error".toResponseBody(),
                    ),
                )
            mockkStatic(::httpExceptionHandling)
            every { httpExceptionHandling(any()) } returns Mocks.getErrorResponse()

            val rideRepository = RideRepository(rideApi)
            val newRide = Mocks.getNewRide()
            val result = rideRepository.estimateRide(newRide)

            expect {
                that(result).isA<Resource.Error<EstimateRideResponse>>()
                that((result as Resource.Error<EstimateRideResponse>).message).isEqualTo("Http Error")
            }
        }

    @Test
    fun `Teste estimateRide UnknownHostException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.estimateRide(any()) } throws UnknownHostException()

            val rideRepository = RideRepository(rideApi)
            val newRide = Mocks.getNewRide()
            val result = rideRepository.estimateRide(newRide)

            expect {
                that(result).isA<Resource.Error<EstimateRideResponse>>()
                that((result as Resource.Error<EstimateRideResponse>).message).isEqualTo("Sem conexão com a internet")
            }
        }

    @Test
    fun `Teste confirmRide sucesso`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            val expected = Mocks.getConfirmRideResponse()
            coEvery { rideApi.confirmRide(any()) } returns expected
            val ride = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.confirmRide(ride, passengerId, driverId)

            expect {
                that(result).isA<Resource.Success<ConfirmRideResponse>>()
                that((result as Resource.Success<ConfirmRideResponse>).data).isEqualTo(expected)
            }
        }

    @Test
    fun `Teste confirmRide Exception`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.confirmRide(any()) } throws Exception()
            val ride = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.confirmRide(ride, passengerId, driverId)

            expect {
                that(result).isA<Resource.Error<ConfirmRideResponse>>()
                that((result as Resource.Error<ConfirmRideResponse>).message).isEqualTo("Erro desconhecido")
            }
        }

    @Test
    fun `Teste confirmRide HttpException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.confirmRide(any()) } throws
                HttpException(
                    Response<String>.error<String>(
                        400,
                        "Http Error".toResponseBody(),
                    ),
                )
            mockkStatic(::httpExceptionHandling)
            every { httpExceptionHandling(any()) } returns Mocks.getErrorResponse()
            val ride = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.confirmRide(ride, passengerId, driverId)

            expect {
                that(result).isA<Resource.Error<ConfirmRideResponse>>()
                that((result as Resource.Error<ConfirmRideResponse>).message).isEqualTo("Http Error")
            }
        }

    @Test
    fun `Teste confirmRide UnknownHostException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.confirmRide(any()) } throws UnknownHostException()
            val ride = Mocks.getEstimateRideResponse()
            val passengerId = "1"
            val driverId = 1

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.confirmRide(ride, passengerId, driverId)

            expect {
                that(result).isA<Resource.Error<ConfirmRideResponse>>()
                that((result as Resource.Error<ConfirmRideResponse>).message).isEqualTo("Sem conexão com a internet")
            }
        }

    @Test
    fun `Teste getRidesHistory sucesso`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            val expected = Mocks.getRideHistoryResponse()
            coEvery { rideApi.getRidesHistory(any(), any()) } returns expected
            val customerId = "1"
            val driverId = "1"

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.getRidesHistory(customerId, driverId)

            expect {
                that(result).isA<Resource.Success<RideHistoryResponse>>()
                that((result as Resource.Success<RideHistoryResponse>).data).isEqualTo(expected)
            }
        }

    @Test
    fun `Teste getRidesHistory Exception`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.getRidesHistory(any(), any()) } throws Exception()
            val customerId = "1"
            val driverId = "1"

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.getRidesHistory(customerId, driverId)

            expect {
                that(result).isA<Resource.Error<RideHistoryResponse>>()
                that((result as Resource.Error<RideHistoryResponse>).message).isEqualTo("Erro desconhecido")
            }
        }

    @Test
    fun `Teste getRidesHistory HttpException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.getRidesHistory(any(), any()) } throws
                HttpException(
                    Response<String>.error<String>(
                        400,
                        "Http Error".toResponseBody(),
                    ),
                )
            mockkStatic(::httpExceptionHandling)
            every { httpExceptionHandling(any()) } returns Mocks.getErrorResponse()
            val customerId = "1"
            val driverId = "1"

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.getRidesHistory(customerId, driverId)

            expect {
                that(result).isA<Resource.Error<RideHistoryResponse>>()
                that((result as Resource.Error<RideHistoryResponse>).message).isEqualTo("Http Error")
            }
        }

    @Test
    fun `Teste getRidesHistory UnknownHostException`() =
        runTest {
            val rideApi = mockk<RideAPI>()
            coEvery { rideApi.getRidesHistory(any(), any()) } throws UnknownHostException()
            val customerId = "1"
            val driverId = "1"

            val rideRepository = RideRepository(rideApi)
            val result = rideRepository.getRidesHistory(customerId, driverId)

            expect {
                that(result).isA<Resource.Error<RideHistoryResponse>>()
                that((result as Resource.Error<RideHistoryResponse>).message).isEqualTo("Sem conexão com a internet")
            }
        }
}
