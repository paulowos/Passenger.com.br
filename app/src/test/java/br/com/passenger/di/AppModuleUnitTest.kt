package br.com.passenger.di

import br.com.passenger.data.network.RideAPI
import br.com.passenger.data.repository.MapRepository
import br.com.passenger.data.repository.RideRepository
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA

class AppModuleUnitTest {
    @Test
    fun `Teste provideRideAPI`() {
        val rideAPI = AppModule.provideRideAPI()

        expectThat(rideAPI).isA<RideAPI>()
    }

    @Test
    fun `Teste provideRideRepository`() {
        val rideRepository = AppModule.provideRideRepository(AppModule.provideRideAPI())

        expectThat(rideRepository).isA<RideRepository>()
    }

    @Test
    fun `Teste provideMapRepository`() {
        val mapRepository = AppModule.provideMapRepository()

        expectThat(mapRepository).isA<MapRepository>()
    }
}
