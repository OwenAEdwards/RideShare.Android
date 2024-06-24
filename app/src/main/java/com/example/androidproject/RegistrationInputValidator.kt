package com.example.androidproject

class RegistrationInputValidator {
    /**
     * Validates if the given email string matches the standard email format.
     * @param email The email string to validate.
     * @return True if the email matches the standard format, otherwise false.
     */
    private fun isValidEmailFormat(email: String): Boolean {
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
     * Validates if the given name string contains only letters from the Latin alphabet.
     * @param name The name string to validate.
     * @return True if the name contains only letters, otherwise false.
     */
    private fun isValidName(name: String): Boolean {
        val nameRegex = Regex("^[a-zA-Z]+$")
        return nameRegex.matches(name)
    }

    /**
     * Validates if the given phone number string matches the standard phone number format.
     * @param phoneNumber The phone number string to validate.
     * @return True if the phone number matches the standard format, otherwise false.
     */
    private fun isValidPhoneNumberFormat(phoneNumber: String): Boolean {
        val phoneRegex = Regex("""^\(\d{3}\) \d{3}-\d{4}$""")
        return phoneRegex.matches(phoneNumber)
    }

    // TODO: Add server-side validation for unique phone number
//    private fun isUniquePhoneNumber(phoneNumber: String): Boolean

    // TODO: Add server-side validation for valid phone number
//    private fun isValidPhoneNumber(phoneNumber: String): Boolean

    /**
     * Validates if the given license plate string matches the standard license plate format.
     * Format: 1-7 uppercase letters or digits.
     * @param licensePlate The license plate string to validate.
     * @return True if the license plate matches the standard format, otherwise false.
     */
    private fun isValidLicensePlate(licensePlate: String): Boolean {
        val licenseRegex = Regex("""^[A-Z0-9]{1,7}$""")
        return licenseRegex.matches(licensePlate)
    }

    // TODO: Add server-side validation for valid car - check VIN number matches year, make, model, license plate, and state
    //                                                - validate the car is not already registered
    //                                                - check proof of insurance
//    private fun isValidCar(year: String, make: String, model: String, licensePlate: String, state: String): Boolean

    /**
     * Validates the registration input for a passenger.
     * @param email The email string to validate.
     * @param password The password string to validate.
     * @param firstName The first name string to validate.
     * @param lastName The last name string to validate.
     * @param phoneNumber The phone number string to validate.
     * @param onValidationError Callback function to handle validation errors.
     * @return True if all input fields are valid, otherwise false.
     */
    fun isValidPassengerRegistration(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        onValidationError: (String) -> Unit
    ): Boolean {
        if (email.isBlank()) {
            onValidationError("Email cannot be empty")
            return false
        }
        if (!isValidEmailFormat(email)) {
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
        if (password.isBlank()) {
            onValidationError("Password cannot be empty")
            return false
        }
        if (!isValidPassword(password)) {
            onValidationError("Password must be 8-20 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
            return false
        }
        if (firstName.isBlank()) {
            onValidationError("First name cannot be empty")
            return false
        }
        if (!isValidName(firstName)) {
            onValidationError("First name must contain only letters")
            return false
        }
        if (lastName.isBlank()) {
            onValidationError("Last name cannot be empty")
            return false
        }
        if (!isValidName(lastName)) {
            onValidationError("Last name must contain only letters")
            return false
        }
        if (phoneNumber.isBlank()) {
            onValidationError("Phone number cannot be empty")
            return false
        }
        if (!isValidPhoneNumberFormat(phoneNumber)) {
            onValidationError("Invalid phone number format")
            return false
        }
        return true
    }

    /**
     * Validates the registration input for a driver.
     * @param email The email string to validate.
     * @param password The password string to validate.
     * @param firstName The first name string to validate.
     * @param lastName The last name string to validate.
     * @param phoneNumber The phone number string to validate.
     * @param year The car year string to validate.
     * @param make The car make string to validate.
     * @param model The car model string to validate.
     * @param licensePlate The car license plate string to validate.
     * @param state The car registration state string to validate.
     * @param onValidationError Callback function to handle validation errors.
     * @return True if all input fields are valid, otherwise false.
     */
    fun isValidDriverRegistration(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        year: String,
        make: String,
        model: String,
        licensePlate: String,
        state: String,
        onValidationError: (String) -> Unit
    ): Boolean {
        if (!isValidPassengerRegistration(email, password, firstName, lastName, phoneNumber, onValidationError)) {
            return false
        }
        if (year.isBlank()) {
            onValidationError("Year cannot be empty")
            return false
        }
        if (make.isBlank()) {
            onValidationError("Make cannot be empty")
            return false
        }
        if (model.isBlank()) {
            onValidationError("Model cannot be empty")
            return false
        }
        if (licensePlate.isBlank()) {
            onValidationError("License plate cannot be empty")
            return false
        }
        if (state.isBlank()) {
            onValidationError("State cannot be empty")
            return false
        }
        return true
    }
}