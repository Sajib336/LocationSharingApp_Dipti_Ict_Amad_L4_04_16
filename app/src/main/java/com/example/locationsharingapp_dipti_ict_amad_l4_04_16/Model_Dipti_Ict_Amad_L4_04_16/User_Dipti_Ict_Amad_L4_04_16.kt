package com.example.locationsharingapp_dipti_ict_amad_l4_04_16.Model_Dipti_Ict_Amad_L4_04_16

import com.google.firebase.database.PropertyName

data class User_Dipti_Ict_Amad_L4_04_16(
    val userId: String,
    @get:PropertyName("displayName")
    @set:PropertyName("displayName")
    var displayName: String = "",

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email: String = "",

    @get:PropertyName("location")
    @set:PropertyName("location")
    var location: String = ""
) {
    // No-argument constructor
    constructor() : this("", "", "")
}