package com.example.parkingspot.di

import android.app.Application
import androidx.room.Room
import com.example.parkingspot.data.ParkingSpotDatabase
import com.example.parkingspot.data.ParkingSpotRepositoryImpl
import com.example.parkingspot.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase =
        Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "parking_spot_db"
        ).build()

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository = ParkingSpotRepositoryImpl(db.dao)
}
