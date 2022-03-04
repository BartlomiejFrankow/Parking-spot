package com.example.parkingspot.domain.useCase

import com.example.parkingspot.domain.model.ParkingSpot
import com.example.parkingspot.domain.repository.ParkingSpotRepository
import javax.inject.Inject

class DeleteSpotUseCase @Inject constructor(private val repo: ParkingSpotRepository) {

    suspend operator fun invoke(spot: ParkingSpot) = repo.deleteParkingSpot(spot)
}
