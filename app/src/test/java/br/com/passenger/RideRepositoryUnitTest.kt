package br.com.passenger

import br.com.passenger.data.dto.EstimateRideResponse
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
}
