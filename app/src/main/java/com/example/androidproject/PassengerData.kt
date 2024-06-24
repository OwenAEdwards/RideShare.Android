package com.example.androidproject

data class PassengerData(
    override val email: String,
    override val password: String,
    override val firstName: String,
    override val lastName: String,
    override val phoneNumber: String
) : UserData(email, password, firstName, lastName, phoneNumber)