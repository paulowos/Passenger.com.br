package br.com.passenger.repository

import br.com.passenger.data.repository.MapRepository
import br.com.passenger.mock.Mocks
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapRepositoryUnitTest {
    @Test
    fun `Teste MapRepository getMap`() {
        val mapRepository = MapRepository("MAPS_API_KEY")

        val height = 100
        val width = 100
        val rideResponse = Mocks.getEstimateRideResponse()

        val result = mapRepository.getMap(height, width, rideResponse)

        val expected =
            "https://maps.googleapis.com/maps/api/staticmap?size=100x100&path=color:0x0000ffff|enc:encodedPolyline&key=MAPS_API_KEY&scale=2&markers=color:green|label:O|0.0,0.0&markers=color:red|label:D|0.0,0.0&language=pt-BR"

        assertEquals(expected, result)
    }
}
