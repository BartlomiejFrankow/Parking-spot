package com.example.parkingspot.presentation

import com.example.parkingspot.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleAssassinsCreedMap : MapEvent()
    data class OnMapLongClick(val latLng: LatLng) : MapEvent()
    data class OnInfoClick(val spot: ParkingSpot) : MapEvent()
}
