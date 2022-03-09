package com.example.parkingspot.data

import com.example.parkingspot.domain.model.ParkingSpot
import com.example.parkingspot.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(private val dao: ParkingSpotDao) : ParkingSpotRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> = dao.getParkingSpots().map { spotEntities ->
        spotEntities.map { spotEntity ->
            spotEntity.toParkingSpot()
        }
    }
}
