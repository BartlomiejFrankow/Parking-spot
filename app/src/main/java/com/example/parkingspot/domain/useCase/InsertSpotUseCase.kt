package com.example.parkingspot.domain.useCase

import com.example.parkingspot.domain.model.ParkingSpot
import com.example.parkingspot.domain.repository.ParkingSpotRepository
import javax.inject.Inject

class InsertSpotUseCase @Inject constructor(private val repo: ParkingSpotRepository) {

    suspend operator fun invoke(spot: ParkingSpot) = repo.insertParkingSpot(spot)
}
