package com.example.parkingspot.domain.useCase

import com.example.parkingspot.domain.repository.ParkingSpotRepository
import javax.inject.Inject

class GetSpotsUseCase @Inject constructor(private val repo: ParkingSpotRepository) {

    suspend operator fun invoke() = repo.getParkingSpots()
}
