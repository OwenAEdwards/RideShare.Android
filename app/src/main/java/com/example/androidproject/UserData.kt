package com.example.androidproject

abstract class UserData(
    open val email: String,
    open val password: String,
    open val firstName: String,
    open val lastName: String,
    open val phoneNumber: String
)