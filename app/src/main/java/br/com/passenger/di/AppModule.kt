package br.com.passenger.di

import br.com.passenger.BuildConfig
import br.com.passenger.data.network.RideAPI
import br.com.passenger.data.repository.MapRepository
import br.com.passenger.data.repository.RideRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRideAPI(): RideAPI =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RideAPI::class.java)

    @Singleton
    @Provides
    fun provideRideRepository(rideAPI: RideAPI): RideRepository = RideRepository(rideAPI)

    @Singleton
    @Provides
    fun provideMapRepository(): MapRepository = MapRepository()
}
