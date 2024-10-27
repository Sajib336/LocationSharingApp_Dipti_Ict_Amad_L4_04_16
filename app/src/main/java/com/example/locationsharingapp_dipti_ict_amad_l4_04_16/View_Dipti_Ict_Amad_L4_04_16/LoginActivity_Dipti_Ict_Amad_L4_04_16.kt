package com.example.locationsharingapp_dipti_ict_amad_l4_04_16.View_Dipti_Ict_Amad_L4_04_16

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.ViewModel_Dipti_Ict_Amad_L4_04_16.AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.databinding.ActivityLoginDiptiIctAmadL40416Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity_Dipti_Ict_Amad_L4_04_16 : AppCompatActivity() {
    private lateinit var binding: ActivityLoginDiptiIctAmadL40416Binding
    private lateinit var authenticationViewModel: AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginDiptiIctAmadL40416Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        authenticationViewModel = ViewModelProvider(this).get(AuthenticationViewModel_Dipti_Ict_Amad_L4_04_16::class.java)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show()
            } else {
                authenticationViewModel.login(email, password, {
                    startActivity(Intent(this, MainActivity_Dipti_Ict_Amad_L4_04_16::class.java))
                    finish()
                }, {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }
        }

        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity_Dipti_Ict_Amad_L4_04_16::class.java))
        }

        binding.forgetPass.setOnClickListener {
            Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this@LoginActivity_Dipti_Ict_Amad_L4_04_16, MainActivity_Dipti_Ict_Amad_L4_04_16::class.java))
            finish()
        }
    }
}