package com.example.parkingspot.data

import com.example.parkingspot.domain.model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(
        id = id,
        lat = lat,
        lng = lng
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        id = id,
        lat = lat,
        lng = lng
    )
}
