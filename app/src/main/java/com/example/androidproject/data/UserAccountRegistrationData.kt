package com.example.androidproject.data

abstract class UserAccountRegistrationData(
    open val email: String,
    open val password: String,
    open val firstName: String,
    open val lastName: String,
    open val phoneNumber: String
)