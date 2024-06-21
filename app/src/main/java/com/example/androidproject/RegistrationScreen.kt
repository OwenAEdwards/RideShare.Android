package com.example.androidproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Composable function to display the registration screen
@Composable
fun RegistrationScreen(validator: RegistrationInputValidator, modifier: Modifier = Modifier) {
    // State variables for storing user inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var isDriverSelected by remember { mutableStateOf(false) } // Account type
    var errorMessage by remember { mutableStateOf("") }

    // Additional state variables for driver account
    var year by remember { mutableStateOf("") }
    var make by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var licensePlate by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    // Column layout for arranging UI elements vertically
    Column(modifier = modifier.padding(16.dp)) {
        // UI elements for user input
        AccountTypeSwitch(onAccountTypeChange = { isDriverSelected = it })
        EmailInput(onEmailChange = { email = it })
        PasswordInput(onPasswordChange = { password = it })
        NameInputs(
            onFirstNameChange = { firstName = it },
            onLastNameChange = { lastName = it }
        )
        PhoneNumberInput(onPhoneNumberChange = { phoneNumber = it })

        // Additional UI elements for driver account input
        if (isDriverSelected) {
            YearInput(onYearChange = { year = it })
            MakeInput(onMakeChange = { make = it })
            ModelInput(onModelChange = { model = it })
            LicensePlateInput(onLicensePlateChange = { licensePlate = it })
            StateInput(onStateChange = { state = it })
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
