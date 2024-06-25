package com.example.androidproject

import com.example.androidproject.data.DriverAccountRegistrationData
import com.example.androidproject.data.PassengerAccountRegistrationData

class RegistrationInputValidator {
    /**
     * Validates if the given email string matches the standard email format
     * and is less than 50 characters long.
     * @param email The email string to validate.
     * @return True if the email matches the standard format and length limit, otherwise false.
     */
    private fun isValidEmailFormat(email: String): Boolean {
        if (email.length > 50) return false
        val emailPattern = Regex(
            "[a-zA-Z0-9+._%\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}"
        )
        return emailPattern.matches(email)
    }

    // TODO: Add server-side validation for unique email address
//    private fun isUniqueEmailAddress(email: String): Boolean

    // TODO: Add server-side validation for valid email address
//    private fun isValidEmailAddress(email: String): Boolean

    // TODO: Allow for server-side password validation to mitigate client-side bypasses and do hashing
    /**
     * Validates if the given password string meets the specified security criteria.
     * Criteria: 8-20 characters long, includes at least one uppercase letter, one lowercase letter, one digit, and one special character.
     * @param password The password string to validate.
     * @return True if the password meets the criteria, otherwise false.
     */
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex(
            """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])\S{8,20}$"""
        )
        return passwordRegex.matches(password)
    }

    // TODO: Allow for other name formats besides those using the Latin alphabet
    // TODO: put a length limit on names, maybe?
    // TODO: Add server-side validation for valid name - make sure of no dirty names and stuff
    /**
     * Validates if the given name string contains only letters from the Latin alphabet
     * and is less than 50 characters long.
     * @param name The name string to validate.
     * @return True if the name contains only letters and adheres to the length limit, otherwise false.
     */
    private fun isValidName(name: String): Boolean {
        if (name.length > 50) return false
        val nameRegex = Regex("^[a-zA-Z]+$")
        return nameRegex.matches(name)
    }

    /**
     * Validates if the given phone number string adheres to either the standard US format
     * (XXX) XXX-XXXX or exactly 10 digits.
     * @param phoneNumber The phone number string to validate.
     * @return True if the phone number matches one of the valid formats, otherwise false.
     */
    private fun isValidPhoneNumberFormat(phoneNumber: String): Boolean {
        val phoneRegex = Regex("""^\(\d{3}\) \d{3}-\d{4}$|(^\d{10}$)""")
        return phoneRegex.matches(phoneNumber)
    }

    // TODO: Add server-side validation for unique phone number
//    private fun isUniquePhoneNumber(phoneNumber: String): Boolean

    // TODO: Add server-side validation for valid phone number
//    private fun isValidPhoneNumber(phoneNumber: String): Boolean

    /**
     * Validates if the given license plate string matches the standard license plate format.
     * Format: 1-8 uppercase letters or digits.
     * @param licensePlate The license plate string to validate.
     * @return True if the license plate matches the standard format, otherwise false.
     */
    private fun isValidLicensePlate(licensePlate: String): Boolean {
        val licenseRegex = Regex("""^[A-Z0-9]{1,8}$""")
        return licenseRegex.matches(licensePlate)
    }

    // TODO: Add server-side validation for valid car - check VIN number matches year, make, model, license plate, and state
    //                                                - validate the car is not already registered
    //                                                - check proof of insurance
//    private fun isValidCar(driverAccountRegistrationData: DriverAccountRegistrationData): Boolean

    /**
     * Validates the registration input for a passenger.
     * This method checks for the following:
     *  - Empty fields (email, password, first name, last name, phone number)
     *  - Invalid email format
     *  - Password complexity (8-20 characters, uppercase, lowercase, digit, special character)
     *  - Name format (only letters)
     *  - Phone number format (implementation required in isValidPhoneNumberFormat)
     *  - Unique email address (server-side validation recommended, commented out)
     *  - Valid email address (server-side validation recommended, commented out)
     *
     * @param passengerAccountRegistrationData An object containing passenger registration information.
     * @param onValidationError Callback function to handle validation errors with a descriptive message.
     * @return True if all input fields are valid, otherwise false.
     */
    fun isValidPassengerRegistration(passengerAccountRegistrationData: PassengerAccountRegistrationData, onValidationError: (String) -> Unit): Boolean {
        if (passengerAccountRegistrationData.email.isBlank()) {
            onValidationError("Email cannot be empty")
            return false
        }
        if (!isValidEmailFormat(passengerAccountRegistrationData.email)) {
            onValidationError("Invalid email format")
            return false
        }
        // TODO: Add server-side validation for unique email address
//        if (!isUniqueEmailAddress(email)) {
//            onValidationError("Email already exists")
//            return false
//        }
        // TODO: Add server-side validation for valid email address
//        if (!isValidEmailAddress(email)) {
//            onValidationError("Invalid email address")
//            return false
//        }
        if (passengerAccountRegistrationData.password.isBlank()) {
            onValidationError("Password cannot be empty")
            return false
        }
        if (!isValidPassword(passengerAccountRegistrationData.password)) {
            onValidationError("Password must be 8-20 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
            return false
        }
        if (passengerAccountRegistrationData.firstName.isBlank()) {
            onValidationError("First name cannot be empty")
            return false
        }
        if (!isValidName(passengerAccountRegistrationData.firstName)) {
            onValidationError("First name must contain only letters")
            return false
        }
        if (passengerAccountRegistrationData.lastName.isBlank()) {
            onValidationError("Last name cannot be empty")
            return false
        }
        if (!isValidName(passengerAccountRegistrationData.lastName)) {
            onValidationError("Last name must contain only letters")
            return false
        }
        if (passengerAccountRegistrationData.phoneNumber.isBlank()) {
            onValidationError("Phone number cannot be empty")
            return false
        }
        if (!isValidPhoneNumberFormat(passengerAccountRegistrationData.phoneNumber)) {
            onValidationError("Invalid phone number format. Please use (XXX) XXX-XXXX or enter exactly 10 digits.")
            return false
        }
        return true
    }

    /**
     * Validates the registration input for a driver.
     *
     * This method follows a two-step validation process:
     *  1. **Passenger Information Validation:**
     *     - It first delegates the validation of common passenger information (email, password, names, phone number)
     *       to the `isValidPassengerRegistration` method. Reusing existing logic reduces code duplication.
     *  2. **Driver-Specific Information Validation:**
     *     - After successful passenger information validation, it checks for the following driver-specific fields:
     *       - Year (ensures it's not empty)
     *       - Make (ensures it's not empty)
     *       - Model (ensures it's not empty)
     *       - License Plate (ensures it's not empty and calls a new method `isValidLicensePlate` for format validation)
     *       - State (ensures it's not empty)
     *       - Server-side validation for valid car
     *
     * @param driverAccountRegistrationData An object containing driver registration information.
     * @param onValidationError Callback function to handle validation errors with a descriptive message.
     * @return True if all input fields are valid, otherwise false.
     */
    fun isValidDriverRegistration(driverAccountRegistrationData: DriverAccountRegistrationData, onValidationError: (String) -> Unit): Boolean {
        if (!isValidPassengerRegistration(PassengerAccountRegistrationData(driverAccountRegistrationData.email, driverAccountRegistrationData.password, driverAccountRegistrationData.firstName, driverAccountRegistrationData.lastName, driverAccountRegistrationData.phoneNumber), onValidationError)) {
            return false
        }
        if (driverAccountRegistrationData.year.isBlank()) {
            onValidationError("Year cannot be empty")
            return false
        }
        if (driverAccountRegistrationData.make.isBlank()) {
            onValidationError("Make cannot be empty")
            return false
        }
        if (driverAccountRegistrationData.model.isBlank()) {
            onValidationError("Model cannot be empty")
            return false
        }
        if (driverAccountRegistrationData.licensePlate.isBlank()) {
            onValidationError("License plate cannot be empty")
            return false
        }
        if (!isValidLicensePlate(driverAccountRegistrationData.licensePlate)) {
            onValidationError("Invalid license plate format")
            return false
        }
        if (driverAccountRegistrationData.state.isBlank()) {
            onValidationError("State cannot be empty")
            return false
        }
        // TODO: Add server-side validation for valid car
//        if (!isValidCar(driverAccountRegistrationData)) {
//            onValidationError("Invalid car information")
//            return false
//        }
        return true
    }
}