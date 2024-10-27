package com.example.locationsharingapp_dipti_ict_amad_l4_04_16

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.FirestoreViewModel_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.Location_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.View_Dipti_Ict_Amad_L4_04_16.LoginActivity_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.View_Dipti_Ict_Amad_L4_04_16.MainActivity_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.databinding.FragmentProfileDiptiIctAmadL40416Binding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment_Dipti_Ict_Amad_L4_04_16 : Fragment() {

    private lateinit var binding: FragmentProfileDiptiIctAmadL40416Binding
    private lateinit var authViewModel: AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
    private lateinit var firestoreViewModel: FirestoreViewModel_Dipti_Ict_Amad_L4_04_16
    private lateinit var locationViewModel: Location_Dipti_Ict_Amad_L4_04_16
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileDiptiIctAmadL40416Binding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16::class.java)
        firestoreViewModel = ViewModelProvider(this).get(FirestoreViewModel_Dipti_Ict_Amad_L4_04_16::class.java)
        locationViewModel = ViewModelProvider(this).get(Location_Dipti_Ict_Amad_L4_04_16::class.java)


        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireContext(), LoginActivity_Dipti_Ict_Amad_L4_04_16::class.java))

        }

        binding.homeBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity_Dipti_Ict_Amad_L4_04_16::class.java))
        }

        loadUserInfo()
        binding.updateBtn.setOnClickListener {
            val newName = binding.NameEt.text.toString()
            val newLocation = binding.Loaction.text.toString()

            updateBtn(newName, newLocation)
        }

        return binding.root
    }


    private fun updateBtn(newName: String, newLocation: String) {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            val userId = currentUser.uid
            firestoreViewModel.updateUser(requireContext(), userId, newName, newLocation)
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity_Dipti_Ict_Amad_L4_04_16::class.java))
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserInfo() {
        val currentUser = authViewModel.getCurrentUser()
        if(currentUser != null) {
            binding.emailEt.setText(currentUser.email)
            firestoreViewModel.getUser(requireContext(), currentUser.uid){ user ->
                if (currentUser.displayName != null) {
                    binding.NameEt.setText(currentUser.displayName)
                }
            }
        }else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
        }

    }
}