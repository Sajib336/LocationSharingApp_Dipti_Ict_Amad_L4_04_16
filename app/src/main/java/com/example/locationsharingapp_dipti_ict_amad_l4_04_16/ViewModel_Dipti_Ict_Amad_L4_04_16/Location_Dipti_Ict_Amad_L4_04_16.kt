package com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16

import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnCompleteListener

class Location_Dipti_Ict_Amad_L4_04_16 : ViewModel() {
    private var fusedLocationClient: FusedLocationProviderClient? = null

    fun getLastLocation(callback: (String) -> Unit) {
        fusedLocationClient?.lastLocation
            ?.addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    val lastLocation = it.result
                    val latitude = lastLocation.latitude
                    val longitude = lastLocation.longitude
                    val location = "Lat: $latitude, Long: $longitude"
                    callback(location)
                } else {
                    // Handle failure or missing permissions
                    callback("Location not available")
                }
            })
    }

    fun initializeFusedLocationClient(client: FusedLocationProviderClient) {
        fusedLocationClient = client
    }
}