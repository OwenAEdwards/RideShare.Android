package com.example.androidproject.data

data class PassengerAccountRegistrationData(
    override val email: String,
    override val password: String,
    override val firstName: String,
    override val lastName: String,
    override val phoneNumber: String
) : UserAccountRegistrationData(email, password, firstName, lastName, phoneNumber)