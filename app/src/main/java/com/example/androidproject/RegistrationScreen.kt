package com.example.androidproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Composable function to display the registration screen
@Composable
fun RegistrationScreen(validator: RegistrationInputValidator, modifier: Modifier = Modifier) {
    // State variables for storing user inputs
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var isDriverSelected by rememberSaveable { mutableStateOf(false) } // Account type
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Additional state variables for driver account
    var year by rememberSaveable { mutableStateOf("") }
    var make by rememberSaveable { mutableStateOf("") }
    var model by rememberSaveable { mutableStateOf("") }
    var licensePlate by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }

    // Column layout for arranging UI elements vertically
    Column(modifier = modifier.padding(16.dp)) {
        // UI elements for user input
        AccountTypeSwitch(onAccountTypeChange = {
            isDriverSelected = it
            errorMessage = "" // Reset error message when switching account type
        })
        EmailInput(onEmailChange = {
            email = it
            errorMessage = "" // Reset error message when input changes
        })
        PasswordInput(onPasswordChange = {
            password = it
            errorMessage = "" // Reset error message when input changes
        })
        NameInputs(
            onFirstNameChange = {
                firstName = it
                errorMessage = "" // Reset error message when input changes
            },
            onLastNameChange = {
                lastName = it
                errorMessage = "" // Reset error message when input changes
            }
        )
        PhoneNumberInput(onPhoneNumberChange = {
            phoneNumber = it
            errorMessage = "" // Reset error message when input changes
        })

        // Additional UI elements for driver account input
        if (isDriverSelected) {
            YearInput(onYearChange = { year = it },
                isError = false // Prevents UI from displaying any error messages or applying error-specific styling when Composable is initially rendered
            )
            MakeInput(onMakeChange = { make = it },
                isError = false // Prevents UI from displaying any error messages or applying error-specific styling when Composable is initially rendered
            )
            ModelInput(onModelChange = { model = it },
                isError = false // Prevents UI from displaying any error messages or applying error-specific styling when Composable is initially rendered
            )
            LicensePlateInput(onLicensePlateChange = {
                licensePlate = it
                errorMessage = "" // Reset error message when input changes
            })
            StateInput(
                onStateChange = { state = it },
                isError = false // Prevents UI from displaying any error messages or applying error-specific styling when Composable is initially rendered
            )
        }

        // Validation logic for registration input
        val isValid = if (isDriverSelected) {
            validator.isValidDriverRegistration(email, password, firstName, lastName, phoneNumber, year, make, model, licensePlate, state) {
                errorMessage = it
            }
        }
        else {
            validator.isValidPassengerRegistration(email, password, firstName, lastName, phoneNumber) {
                errorMessage = it
            }
        }

        // Display error message if any validation errors occur
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        // Register button
        RegistrationButton(
            onClick = {
                // Handle registration logic based on account type
                if (isValid) {
                    if (isDriverSelected) {
                        registerDriver(email, password, firstName, lastName, phoneNumber, year, make, model, licensePlate, state)
                    }
                    else {
                        registerPassenger(email, password, firstName, lastName, phoneNumber)
                    }
                } else {
                    // Show error messages for invalid fields
                    // TODO: these would be errors from server-side API calls made after calls to either
                    //  - registerPassenger() or registerDriver()
                }
            },
            enabled = isValid // Enable button only if all fields are valid
        )
    }
}

// Placeholder function to simulate registration process (replace with your actual logic)
fun registerPassenger(
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    phoneNumber: String
) {
    // Implement registration logic using email, password, etc.
    // TODO: add API call logic here
}

fun registerDriver(
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    phoneNumber: String,
    year: String,
    make: String,
    model: String,
    licensePlate: String,
    state: String
) {
    registerPassenger(email, password, firstName, lastName, phoneNumber)
    // Implement registration logic using email, password, etc.
    // TODO: add API call logic here
}

// Preview Composable for displaying the RegistrationScreen in Android Studio
@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(RegistrationInputValidator())
}
