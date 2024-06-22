package com.example.androidproject

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RegistrationInputValidatorTest {

    @Mock
    lateinit var mockValidationErrorCallback: (String) -> Unit

    private lateinit var validator: RegistrationInputValidator

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        validator = RegistrationInputValidator()
    }

    @Test
    fun testValidPassengerRegistration() {
        assertTrue(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "Passw0rd!",
                "John",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
    }

    @Test
    fun testEmptyEmail() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "",
                "Passw0rd!",
                "John",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Email cannot be empty")
    }

    @Test
    fun testInvalidEmailFormat() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "invalidemail",
                "Passw0rd!",
                "John",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Invalid email format")
    }

    // TODO: Add unit test for unique email address
//    @Test
//    fun testUniqueEmailAddress()

    // TODO: Add unit test for valid email address
//    @Test
//    fun testValidEmailAddress()

    @Test
    fun testEmptyPassword() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "",
                "John",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Password cannot be empty")
    }

    @Test
    fun testInvalidPassword() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "password",
                "John",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Password must be 8-20 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    }

    @Test
    fun testEmptyFirstName() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "Passw0rd!",
                "",
                "Doe",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("First name cannot be empty")
    }

    // TODO: Add unit test for invalid first name
//    @Test
//    fun testInvalidFirstName()

    @Test
    fun testEmptyLastName() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "Passw0rd!",
                "John",
                "",
                "(123) 456-7890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Last name cannot be empty")
    }

    // TODO: Add unit test for invalid last name
//    @Test
//    fun testInvalidLastName()

    @Test
    fun testEmptyPhoneNumber() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "Passw0rd!",
                "John",
                "Doe",
                "",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Phone number cannot be empty")
    }

    @Test
    fun testInvalidPhoneNumberFormat() {
        assertFalse(
            validator.isValidPassengerRegistration(
                "test@example.com",
                "Passw0rd!",
                "John",
                "Doe",
                "1234567890",
                mockValidationErrorCallback
            )
        )
        verify(mockValidationErrorCallback).invoke("Invalid phone number format")
    }

    // TODO: Add unit test for unique phone number
//    @Test
//    fun testUniquePhoneNumber()

    // TODO: Add unit test for valid phone number
//    @Test
//    fun testValidPhoneNumber()

    // Add more test cases as needed

    @Test
    fun testValidDriverRegistration() {
        assertTrue(
            validator.isValidDriverRegistration(
                "test@example.com",
                "Passw0rd!",
                "John",
                "Doe",
                "(123) 456-7890",
                "2020",
                "Toyota",
                "Camry",
                "ABC123",
                "CA",
                mockValidationErrorCallback
            )
        )
    }

    // TODO: Test invalid DriverRegistration - no point in writing them because I want to get a combo box of valid year, make, and model for a car
    //  - validating license plate and state together should be a separate test too (including checking if the license plate field is empty, state should be a combo box too)

    // TODO: Add tests for API calls made within RegistrationInputValidator
}
