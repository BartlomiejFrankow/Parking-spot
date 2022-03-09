package com.example.parkingspot.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.parkingspot.R
import com.example.parkingspot.domain.model.ParkingSpot
import com.example.parkingspot.presentation.MapEvent.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val mapUiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(ToggleAssassinsCreedMap) },
                backgroundColor = colorResource(R.color.assassin_mid_green)
            ) {
                Icon(
                    imageVector = if (viewModel.state.isAssassinsCreedMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Assassins map"
                )
            }
        }
    ) {
        Box {
            var openDialog by remember { mutableStateOf(false) }
            var spotToDelete by remember { mutableStateOf<ParkingSpot?>(null) }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = viewModel.state.properties,
                uiSettings = mapUiSettings,
                onMapLongClick = { viewModel.onEvent(OnMapLongClick(it)) }
            ) {
                viewModel.state.parkingSpots.forEach { spot ->
                    Marker(
                        position = LatLng(spot.lat, spot.lng),
                        title = stringResource(R.string.parking_spot_title, spot.lat, spot.lng),
                        snippet = stringResource(R.string.click_to_delete_info),
                        onInfoWindowClick = {
                            spotToDelete = spot
                            openDialog = true
                        },
                        onClick = {
                            it.showInfoWindow()
                            true
                        },
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )
                }
            }

            if (openDialog && spotToDelete != null) {
                DeleteMarkerDialog(viewModel, spotToDelete!!) { dialogState -> openDialog = dialogState }
            }
        }
    }
}

@Composable
fun DeleteMarkerDialog(viewModel: MapViewModel, spot: ParkingSpot, isDialogOpened: (Boolean) -> Unit) {
    MaterialTheme {
        Column {
            AlertDialog(
                onDismissRequest = { isDialogOpened(false) },
                title = {
                    Text(text = stringResource(R.string.warning))
                },
                text = {
                    Text(text = stringResource(R.string.delete_marker_question))
                },
                dismissButton = {
                    Button(
                        onClick = { isDialogOpened(false) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.assassin_mid_green),
                            contentColor = Color.White
                        )
                    ) {
                        Text(stringResource(R.string.dismiss))
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.onEvent(OnInfoClick(spot))
                            isDialogOpened(false)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.assassin_mid_green),
                            contentColor = Color.White
                        )
                    ) {
                        Text(stringResource(R.string.delete))
                    }
                }
            )
        }
    }
}
