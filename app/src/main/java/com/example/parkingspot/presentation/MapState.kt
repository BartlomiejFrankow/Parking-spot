package com.example.parkingspot.presentation

import com.example.parkingspot.domain.model.ParkingSpot
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val properties: MapProperties = MapProperties(mapStyleOptions = MapStyleOptions(MapStyle.assassinsCreedMapJson)),
    val isAssassinsCreedMap: Boolean = true
)
