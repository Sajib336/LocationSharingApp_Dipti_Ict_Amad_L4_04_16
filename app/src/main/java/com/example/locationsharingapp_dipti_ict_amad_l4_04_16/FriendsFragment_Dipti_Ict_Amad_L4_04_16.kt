package com.example.locationsharingapp_dipti_ict_amad_l4_04_16

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.Adapter_Dipti_Ict_Amad_L4_04_16.UserAdapter_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.FirestoreViewModel_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.Location_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.View_Dipti_Ict_Amad_L4_04_16.MapsActivity_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.databinding.FragmentFriendsDiptiIctAmadL40416Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class FriendsFragment_Dipti_Ict_Amad_L4_04_16 : Fragment() {
    private lateinit var binding: FragmentFriendsDiptiIctAmadL40416Binding
    private lateinit var firestoreViewModel: FirestoreViewModel_Dipti_Ict_Amad_L4_04_16
    private lateinit var authenticationViewModel: AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
    private lateinit var userAdapter: UserAdapter_Dipti_Ict_Amad_L4_04_16
    private lateinit var locationViewModel: Location_Dipti_Ict_Amad_L4_04_16
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocation()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentFriendsDiptiIctAmadL40416Binding.inflate(inflater,container, false)

        firestoreViewModel = ViewModelProvider(this)[FirestoreViewModel_Dipti_Ict_Amad_L4_04_16::class.java]
        locationViewModel = ViewModelProvider(this)[Location_Dipti_Ict_Amad_L4_04_16::class.java]
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16::class.java]
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationViewModel.initializeFusedLocationClient(fusedLocationClient)

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the permission
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // Permission is already granted
            getLocation()
        }
        userAdapter = UserAdapter_Dipti_Ict_Amad_L4_04_16(emptyList())
        binding.userRV.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        fetchUsers()

        binding.locationBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MapsActivity_Dipti_Ict_Amad_L4_04_16::class.java))
        }


        return binding.root
    }

    private fun fetchUsers() {
        firestoreViewModel.getAllUsers(requireContext()){
            userAdapter.updateData(it)
        }
    }

    private fun getLocation() {
        locationViewModel.getLastLocation {
            // Save location to Firestore for the current user
            authenticationViewModel.getCurrentUserId().let { userId ->
                firestoreViewModel.updateUserLocation(requireContext(),userId, it)
            }
        }
    }

}