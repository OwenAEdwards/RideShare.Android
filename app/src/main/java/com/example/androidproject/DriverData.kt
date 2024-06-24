package com.example.androidproject

data class DriverData(
    override val email: String,
    override val password: String,
    override val firstName: String,
    override val lastName: String,
    override val phoneNumber: String,
    val year: String,
    val make: String,
    val model: String,
    val licensePlate: String,
    val state: String
) : UserData(email, password, firstName, lastName, phoneNumber)
