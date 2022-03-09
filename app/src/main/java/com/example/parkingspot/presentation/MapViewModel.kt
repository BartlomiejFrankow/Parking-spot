package com.example.parkingspot.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingspot.domain.model.ParkingSpot
import com.example.parkingspot.domain.useCase.DeleteSpotUseCase
import com.example.parkingspot.domain.useCase.GetSpotsUseCase
import com.example.parkingspot.domain.useCase.InsertSpotUseCase
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val insertSpotUseCase: InsertSpotUseCase,
    private val deleteSpotUseCase: DeleteSpotUseCase,
    private val getSpotsUseCase: GetSpotsUseCase
) : ViewModel() {
    var state by mutableStateOf(MapState())

    init {
        getMapStyle()

        viewModelScope.launch {
            getSpotsUseCase().collectLatest { spots ->
                state = state.copy(parkingSpots = spots)
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.ToggleAssassinsCreedMap -> {
                state = state.copy(
                    properties = state.properties.copy(mapStyleOptions = getMapStyle()),
                    isAssassinsCreedMap = !state.isAssassinsCreedMap
                )
            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    insertSpotUseCase(ParkingSpot(event.latLng.latitude, event.latLng.longitude))
                }
            }
            is MapEvent.OnInfoClick -> {
                viewModelScope.launch {
                    deleteSpotUseCase(event.spot)
                }
            }
        }
    }

    private fun getMapStyle() = if (state.isAssassinsCreedMap) null else MapStyleOptions(MapStyle.assassinsCreedMapJson)

}
