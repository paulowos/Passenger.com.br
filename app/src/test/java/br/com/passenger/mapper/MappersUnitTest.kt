package br.com.passenger.mapper

import br.com.passenger.data.dto.toConfirmRideRequest
import br.com.passenger.data.dto.toRider
import br.com.passenger.mock.Mocks
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

class MappersUnitTest {
    @Test
    fun `Teste EstimateRideResponse Option toRider`() {
        val option = Mocks.getEstimateRideResponseOption()

        val rider = option.toRider()

        expect {
            that(rider).isNotNull()
            that(rider.id).isEqualTo(option.id)
            that(rider.name).isEqualTo(option.name)
            that(rider.description).isEqualTo(option.description)
            that(rider.vehicle).isEqualTo(option.vehicle)
            that(rider.price).isEqualTo(option.value)
            that(rider.review).isEqualTo(option.review.rating)
        }
    }

    @Test
    fun `Teste EstimateRideResponse toConfirmRideRequest`() {
        val estimateRideResponse = Mocks.getEstimateRideResponse()
        val passengerId = "1"
        val driverId = 1
        val confirmRideRequest = estimateRideResponse.toConfirmRideRequest(passengerId, driverId)

        expect {
            that(confirmRideRequest).isNotNull()
            that(confirmRideRequest.destination).isEqualTo(estimateRideResponse.destination.toString())
            that(confirmRideRequest.distance).isEqualTo(estimateRideResponse.distance / 1000)
            that(confirmRideRequest.duration).isEqualTo(estimateRideResponse.duration.toString())
            that(confirmRideRequest.origin).isEqualTo(estimateRideResponse.origin.toString())
            that(confirmRideRequest.customerId).isEqualTo(passengerId)
            that(confirmRideRequest.driver).isNotNull()
            that(confirmRideRequest.driver.id).isEqualTo(driverId.toString())
            that(confirmRideRequest.driver.name).isEqualTo(estimateRideResponse.options.find { it.id == driverId }?.name)
            that(confirmRideRequest.value).isEqualTo(estimateRideResponse.options.find { it.id == driverId }?.value)
        }
    }

    @Test
    fun `Teste EstimateRideResponse toConfirmRideRequest - Driver Vazio`() {
        val estimateRideResponse = Mocks.getEstimateRideResponse(isOptionEmpty = true)
        val passengerId = "1"
        val driverId = 2
        val confirmRideRequest = estimateRideResponse.toConfirmRideRequest(passengerId, driverId)

        expect {
            that(confirmRideRequest).isNotNull()
            that(confirmRideRequest.destination).isEqualTo(estimateRideResponse.destination.toString())
            that(confirmRideRequest.distance).isEqualTo(estimateRideResponse.distance / 1000)
            that(confirmRideRequest.duration).isEqualTo(estimateRideResponse.duration.toString())
            that(confirmRideRequest.origin).isEqualTo(estimateRideResponse.origin.toString())
            that(confirmRideRequest.customerId).isEqualTo(passengerId)
            that(confirmRideRequest.driver).isNotNull()
            that(confirmRideRequest.driver.id).isEqualTo(driverId.toString())
            that(confirmRideRequest.driver.name).isEqualTo("")
            that(confirmRideRequest.value).isEqualTo(0.0)
        }
    }
}
