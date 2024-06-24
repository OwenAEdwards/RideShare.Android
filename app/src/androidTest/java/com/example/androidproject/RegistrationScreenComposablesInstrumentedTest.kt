package com.example.androidproject

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidproject.ui.theme.AndroidProjectTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationScreenComposablesInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Tests the AccountTypeSwitch component's functionality.
     * Verifies the behavior of switching between account types and the callback function.
     */
    @Test
    fun testAccountTypeSwitch() {
        var isDriver = false
        composeTestRule.setContent {
            AndroidProjectTheme {
                AccountTypeSwitch(
                    onAccountTypeChange = { newIsDriver ->
                        isDriver = newIsDriver
                    }
                )
            }
        }

        // Verify initial state
        composeTestRule.onNodeWithText("Account Type").assertIsDisplayed()
        composeTestRule.onNodeWithTag("account_type_switch").assertIsOff()

        // Perform click to change state
        composeTestRule.onNodeWithTag("account_type_switch").performClick()

        // Verify state after click
        composeTestRule.onNodeWithTag("account_type_switch").assertIsOn()
        assert(isDriver) { "Callback was not invoked with correct value" }

        // Perform another click to change state back
        composeTestRule.onNodeWithTag("account_type_switch").performClick()

        // Verify state after second click
        composeTestRule.onNodeWithTag("account_type_switch").assertIsOff()
        assert(!isDriver) { "Callback was not invoked with correct value" }
    }

    /**
     * Tests the EmailInput component's functionality.
     * Verifies text input, error handling, and callback function for email input.
     */
    @Test
    fun testEmailInput() {
        var enteredEmail = ""
        composeTestRule.setContent {
            AndroidProjectTheme {
                EmailInput(onEmailChange = { newEmail ->
                    enteredEmail = newEmail
                })
            }
        }

        // Verify initial state
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithTag("email_input").assertTextEquals("")

        // Perform text input
        val testEmail = "test@example.com"
        composeTestRule.onNodeWithTag("email_input").performTextInput(testEmail)

        // Verify text input
        composeTestRule.onNodeWithTag("email_input").assertTextEquals(testEmail)

        // Verify callback invocation
        assert(enteredEmail == testEmail) { "Callback was not invoked with correct value" }

        // Test error state
        composeTestRule.setContent {
            AndroidProjectTheme {
                EmailInput(
                    onEmailChange = {},
                    modifier = Modifier.testTag("email_input_error"),
                    isError = true,
                    errorMessage = "Invalid email address"
                )
            }
        }

        // Verify error message
        composeTestRule.onNodeWithTag("email_input_error").assertIsDisplayed()
        composeTestRule.onNodeWithText("Invalid email address").assertIsDisplayed()
    }

    /**
     * Tests the PasswordInput component's functionality.
     * Verifies text input, error handling, and callback function for password input.
     */
    @Test
    fun testPasswordInput() {
        var enteredPassword = ""
        val errorMessage = "Password must be 8-20 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character"

        composeTestRule.setContent {
            AndroidProjectTheme {
                PasswordInput(
                    onPasswordChange = { newPassword ->
                        enteredPassword = newPassword
                    },
                    modifier = Modifier.testTag("password_input"),
                    isError = false,
                    errorMessage = null
                )
            }
        }

        // Verify initial state
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithTag("password_input").assertTextEquals("")

        // Perform text input
        val testPassword = "password123"
        composeTestRule.onNodeWithTag("password_input").performTextInput(testPassword)

        // Verify text input
        composeTestRule.onNodeWithTag("password_input").assertTextEquals(testPassword)

        // Verify callback invocation
        assert(enteredPassword == testPassword) { "Callback was not invoked with correct value" }

        // Set content with error state
        composeTestRule.setContent {
            AndroidProjectTheme {
                PasswordInput(
                    onPasswordChange = { newPassword ->
                        enteredPassword = newPassword
                    },
                    modifier = Modifier.testTag("password_input_error"),
                    isError = true,
                    errorMessage = errorMessage
                )
            }
        }

        // Verify error message
        composeTestRule.onNodeWithTag("password_input_error").assertIsDisplayed()
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    /**
     * Tests the NameInputs component's functionality.
     * Verifies text input and callback function for first name and last name inputs.
     */
    @Test
    fun testNameInputs() {
        var enteredFirstName = ""
        var enteredLastName = ""

        composeTestRule.setContent {
            AndroidProjectTheme {
                NameInputs(
                    onFirstNameChange = { newFirstName ->
                        enteredFirstName = newFirstName
                    },
                    onLastNameChange = { newLastName ->
                        enteredLastName = newLastName
                    }
                )
            }
        }

        // Verify first name input field
        composeTestRule.onNodeWithText("First Name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("first_name_input").assertTextEquals("")

        // Perform text input for first name
        val firstName = "John"
        composeTestRule.onNodeWithTag("first_name_input").performTextInput(firstName)

        // Verify text input for first name
        composeTestRule.onNodeWithTag("first_name_input").assertTextEquals(firstName)

        // Verify callback invocation for first name
        assert(enteredFirstName == firstName) { "Callback for first name was not invoked with correct value" }

        // Verify last name input field
        composeTestRule.onNodeWithText("Last Name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("last_name_input").assertTextEquals("")

        // Perform text input for last name
        val lastName = "Doe"
        composeTestRule.onNodeWithTag("last_name_input").performTextInput(lastName)

        // Verify text input for last name
        composeTestRule.onNodeWithTag("last_name_input").assertTextEquals(lastName)

        // Verify callback invocation for last name
        assert(enteredLastName == lastName) { "Callback for last name was not invoked with correct value" }
    }

    /**
     * Tests the PhoneNumberInput component's functionality.
     * Verifies text input, formatting, and callback function for phone number input.
     */
    @Test
    fun testPhoneNumberInput() {
        var enteredPhoneNumber = ""

        composeTestRule.setContent {
            AndroidProjectTheme {
                PhoneNumberInput(
                    onPhoneNumberChange = { newPhoneNumber ->
                        enteredPhoneNumber = newPhoneNumber
                    }
                )
            }
        }

        // Verify phone number input field
        composeTestRule.onNodeWithText("Phone Number").assertIsDisplayed()
        composeTestRule.onNodeWithTag("phone_number_input").assertTextEquals("")

        // Perform text input for phone number without formatting
        val phoneNumberRaw = "1234567890"
        composeTestRule.onNodeWithTag("phone_number_input").performTextInput(phoneNumberRaw)

        // Verify text input for phone number without formatting
        composeTestRule.onNodeWithTag("phone_number_input").assertTextEquals("(123) 456-7890")

        // Verify callback invocation for phone number without formatting
        assert(enteredPhoneNumber == phoneNumberRaw) { "Callback for phone number (without formatting) was not invoked with correct value" }

        // Perform text input for formatted phone number
        val phoneNumberFormatted = "(987) 654-3210"
        composeTestRule.onNodeWithTag("phone_number_input").performTextInput(phoneNumberFormatted)

        // Verify text input for formatted phone number
        composeTestRule.onNodeWithTag("phone_number_input").assertTextEquals("(987) 654-3210")

        // Verify callback invocation for formatted phone number
        assert(enteredPhoneNumber == phoneNumberFormatted.replace(Regex("[^\\d]"), "")) { "Callback for phone number (formatted) was not invoked with correct value" }
    }

    /**
     * Tests the YearInput component's functionality.
     * Verifies dropdown behavior, year selection, and callback function for year input.
     */
    // TODO: Rewrite test after properly implementing correct year, make, and model dropdown logic
    @Test
    fun testYearInput() {
        var selectedYear = ""

        // Set up the Composable under test
        composeTestRule.setContent {
            YearInput(
                modifier = Modifier.testTag("year_input"),
                onYearChange = { selectedYear = it },
                isError = false
            )
        }

        // Verify that the YearInput composable is displayed
        composeTestRule.onNodeWithTag("year_input").assertExists()

        // Verify the initial selected year
        composeTestRule.onNodeWithText("Select Year").assertIsDisplayed()

        // Click on the YearInput to expand the ComboBox
        composeTestRule.onNodeWithTag("year_input").performClick()

        // Verify that the ComboBox options are displayed
        composeTestRule.onAllNodesWithText("1980", substring = true).assertCountEquals(1)
        composeTestRule.onAllNodesWithText("2023", substring = true).assertCountEquals(1)
        // Verify other years as needed

        // Select a year from the ComboBox options
        composeTestRule.onNodeWithText("2020", substring = true).performClick()

        // Verify that the selected year is updated
        assert(selectedYear == "2020")
    }

    /**
     * Tests the MakeInput component's functionality.
     * Verifies dropdown behavior, make selection, and callback function for make input.
     */
    // TODO: Rewrite test after properly implementing correct year, make, and model dropdown logic
    @Test
    fun testMakeInput() {
        var selectedMake = ""

        // Set up the Composable under test
        composeTestRule.setContent {
            MakeInput(
                modifier = Modifier.testTag("make_input"),
                onMakeChange = { selectedMake = it },
                isError = false
            )
        }

        // Verify that the MakeInput composable is displayed
        composeTestRule.onNodeWithTag("make_input").assertExists()

        // Verify the initial selected make
        composeTestRule.onNodeWithText("Select Make").assertIsDisplayed()

        // Click on the MakeInput to expand the ComboBox
        composeTestRule.onNodeWithTag("make_input").performClick()

        // Verify that the ComboBox options are displayed
        composeTestRule.onAllNodesWithText("Toyota", substring = true).assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Audi", substring = true).assertCountEquals(1)
        // Verify other makes as needed

        // Select a make from the ComboBox options
        composeTestRule.onNodeWithText("Ford", substring = true).performClick()

        // Verify that the selected make is updated
        assert(selectedMake == "Ford")
    }

    /**
     * Tests the ModelInput component's functionality.
     * Verifies dropdown behavior, model selection, and callback function for model input.
     */
    // TODO: Rewrite test after properly implementing correct year, make, and model dropdown logic
    @Test
    fun testModelInput() {
        var selectedModel = ""

        // Set up the Composable under test
        composeTestRule.setContent {
            ModelInput(
                modifier = Modifier.testTag("model_input"),
                onModelChange = { selectedModel = it },
                isError = false
            )
        }

        // Verify that the ModelInput composable is displayed
        composeTestRule.onNodeWithTag("model_input").assertExists()

        // Verify the initial selected model
        composeTestRule.onNodeWithText("Select Model").assertIsDisplayed()

        // Click on the ModelInput to expand the ComboBox
        composeTestRule.onNodeWithTag("model_input").performClick()

        // Verify that the ComboBox options are displayed
        composeTestRule.onAllNodesWithText("Model S", substring = true).assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Model Y", substring = true).assertCountEquals(1)
        // Verify other models as needed

        // Select a model from the ComboBox options
        composeTestRule.onNodeWithText("Model 3", substring = true).performClick()

        // Verify that the selected model is updated
        assert(selectedModel == "Model 3")
    }


    /**
     * Tests the LicensePlateInput component's functionality.
     * Verifies text input and callback function for license plate input.
     */
    @Test
    fun testLicensePlateInput() {
        var enteredLicensePlate = ""

        composeTestRule.setContent {
            AndroidProjectTheme {
                LicensePlateInput(
                    onLicensePlateChange = { newLicensePlate ->
                        enteredLicensePlate = newLicensePlate
                    }
                )
            }
        }

        // Verify license plate input field
        composeTestRule.onNodeWithText("License Plate").assertIsDisplayed()
        composeTestRule.onNodeWithTag("license_plate_input").assertTextEquals("")

        // Perform text input for license plate
        val licensePlate = "ABC1234"
        composeTestRule.onNodeWithTag("license_plate_input").performTextInput(licensePlate)

        // Verify text input for license plate
        composeTestRule.onNodeWithTag("license_plate_input").assertTextEquals("ABC1234")

        // Verify callback invocation for license plate
        assert(enteredLicensePlate == licensePlate) { "Callback for license plate was not invoked with correct value" }
    }

    /**
     * Tests the StateInput component's functionality.
     * Verifies dropdown behavior, selection of states, and callback function for state input.
     */
    @Test
    fun testStateInput() {
        var selectedState = ""

        composeTestRule.setContent {
            StateInput(
                modifier = Modifier.testTag("state_input"),
                onStateChange = { selectedState = it },
                isError = false
            )
        }

        // Verify that the StateInput composable is displayed
        composeTestRule.onNodeWithTag("state_input").assertExists()

        // Verify the initial selected state
        composeTestRule.onNodeWithText("Select State").assertIsDisplayed()

        // Click on the StateInput to expand the ComboBox
        composeTestRule.onNodeWithTag("state_input").performClick()

        // Verify that the ComboBox options are displayed
        composeTestRule.onAllNodesWithText("AL", substring = true).assertCountEquals(1)
        composeTestRule.onAllNodesWithText("AK", substring = true).assertCountEquals(1)
        // Verify other states as needed

        // Select a state from the ComboBox options
        composeTestRule.onNodeWithText("AK", substring = true).performClick()

        // Verify that the selected state is updated
        assert(selectedState == "AK")
    }

    /**
     * Tests the RegistrationButton component's functionality.
     * Verifies button click behavior and validation messages.
     */
    @Test
    fun testRegistrationButton() {
        // Set up the content with AndroidProjectTheme
        composeTestRule.setContent {
            AndroidProjectTheme {
                RegistrationButton(
                    onClick = {},
                    enabled = true,
                    validationFailed = false,
                    validationMessage = "Validation failed"
                )
            }
        }

        // Verify registration button text is displayed
        composeTestRule.onNodeWithText("Register").assertIsDisplayed()

        // Verify registration button is clickable and performs click action
        composeTestRule.onNodeWithText("Register").performClick()

        // Verify that the validation message is not displayed initially
        composeTestRule.onNodeWithText("Validation failed").assertDoesNotExist()

        // Update the content with validation failure
        composeTestRule.setContent {
            AndroidProjectTheme {
                RegistrationButton(
                    onClick = {},
                    enabled = true,
                    validationFailed = true,
                    validationMessage = "Validation failed"
                )
            }
        }

        // Verify that the validation message is now displayed
        composeTestRule.onNodeWithText("Validation failed").assertIsDisplayed()
    }
}
