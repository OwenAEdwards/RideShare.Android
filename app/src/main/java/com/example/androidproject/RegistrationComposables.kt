package com.example.androidproject

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Composable function for selecting account type (Driver/Passenger) with a switch.
 *
 * @param modifier Modifier for the Row layout.
 * @param onAccountTypeChange Callback function to handle the change in account type selection.
 */
@Composable
fun AccountTypeSwitch(
    modifier: Modifier = Modifier,
    onAccountTypeChange: (Boolean) -> Unit, // Callback for account type selection (driver/passenger)
) {
    var isDriver by remember { mutableStateOf(false) } // Initial state: Passenger (false)

    Row(modifier = modifier) {
        Text(text = stringResource(R.string.account_type))
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = isDriver,
            onCheckedChange = { newIsDriver: Boolean ->
                try {
                    isDriver = newIsDriver
                    onAccountTypeChange(newIsDriver)
                }
                catch (e: Exception) {
                    Log.e("AccountTypeSwitch", "Error changing account type", e)
                }
            },
            colors = SwitchDefaults.colors(), // Use default colors
            modifier = Modifier.testTag("account_type_switch")
        )
    }
}

/**
 * Base composable function for text input fields with error handling.
 *
 * @param modifier Modifier for the TextField.
 * @param value Current value of the text field.
 * @param onValueChange Callback function to handle text value changes.
 * @param label Optional composable function for the text field label.
 * @param keyboardType Type of keyboard to be used for input.
 * @param isError Flag indicating if the input field is in an error state.
 * @param errorMessage Optional error message to display when in an error state.
 */
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String?,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String? = null, // Add an optional error message
) {
    Column {
        TextField(
            modifier = modifier,
            value = value ?: "",
            onValueChange = onValueChange,
            label = label,
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


/**
 * Composable function for an email input field.
 *
 * @param modifier Modifier for the TextInput, allowing customization of the component's appearance and behavior.
 * @param onEmailChange Callback function to handle changes to the email input.
 * @param isError Boolean indicating whether there is an error with the email input. Default is false.
 * @param errorMessage Optional error message to display if there is an error. Default is null.
 */
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    var emailState by remember { mutableStateOf<String?>(null) }
    TextInput(
        modifier = modifier.testTag("email_input"),
        value = emailState,
        onValueChange = { newEmail ->
            try {
                emailState = newEmail
                onEmailChange(newEmail)
            }
            catch (e: Exception) {
                Log.e("EmailInput", "Error changing email", e)
            }
        },
        label = { Text(stringResource(R.string.email)) },
        keyboardType = KeyboardType.Email,
        isError = isError,
        errorMessage = errorMessage
    )
}


/**
 * Composable function for a password input field.
 *
 * @param modifier Modifier for the TextInput, allowing customization of the component's appearance and behavior.
 * @param onPasswordChange Callback function to handle changes to the password input.
 * @param isError Boolean indicating whether there is an error with the password input. Default is false.
 * @param errorMessage Optional error message to display if there is an error. Default is null.
 */
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    onPasswordChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    var passwordState by remember { mutableStateOf<String?>(null) }
    TextInput(
        modifier = modifier.testTag("password_input"),
        value = passwordState,
        onValueChange = { newPassword ->
            try {
                passwordState = newPassword
                onPasswordChange(newPassword)
            } catch (e: Exception) {
                Log.e("PasswordInput", "Error changing password", e)
            }
        },
        label = { Text(stringResource(R.string.password)) },
        keyboardType = KeyboardType.Password,
        isError = isError,
        errorMessage = errorMessage
    )
}


/**
 * Composable function for first name and last name input fields.
 *
 * @param modifier Modifier for the Column layout.
 * @param onFirstNameChange Callback function to handle first name changes.
 * @param onLastNameChange Callback function to handle last name changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun NameInputs(
    modifier: Modifier = Modifier,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = modifier) {
        var firstNameState by remember { mutableStateOf<String?>(null) } // State variable for first name
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = firstNameState,
            onValueChange = { newFirstName ->
                try {
                    firstNameState = newFirstName
                    onFirstNameChange(newFirstName)
                }
                catch (e: Exception) {
                    Log.e("NameInputs", "Error changing first name", e)
                }
            },
            label = { Text(stringResource(R.string.first_name)) },
            isError = isError,
            errorMessage = errorMessage
        )
        Spacer(modifier = Modifier.height(8.dp))
        var lastNameState by remember { mutableStateOf("") } // State variable for last name
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = lastNameState,
            onValueChange = { newLastName ->
                try {
                    lastNameState = newLastName
                    onLastNameChange(newLastName)
                }
                catch (e: Exception) {
                    Log.e("NameInputs", "Error changing last name", e)
                }
            },
            label = { Text(stringResource(R.string.last_name)) },
            isError = isError,
            errorMessage = errorMessage
        )
    }
}

/**
 * Composable function for phone number input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onPhoneNumberChange Callback function to handle phone number changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun PhoneNumberInput(
    modifier: Modifier = Modifier,
    onPhoneNumberChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var phoneNumber by remember { mutableStateOf("") } // State variable for phone number

    // Function to format phone number with parentheses and dashes (US format)
    fun formatPhoneNumber(number: String): String {
        val formattedNumber = number.replace(Regex("\\D"), "") // Remove non-digits
        return when (formattedNumber.length) {
            0 -> ""
            in 1..3 -> formattedNumber
            in 4..6 -> "(${formattedNumber.substring(0, 3)}) ${formattedNumber.substring(3)}"
            else -> "(${formattedNumber.substring(0, 3)}) ${formattedNumber.substring(3, 6)}-${formattedNumber.substring(6)}"
        }
    }

    TextInput(
        modifier = modifier,
        value = phoneNumber,
        onValueChange = { newNumber ->
            try {
                phoneNumber = newNumber
                val formattedNumber = formatPhoneNumber(newNumber)
                onPhoneNumberChange(formattedNumber) // Pass formatted number to callback
            }
            catch (e: Exception) {
                Log.e("PhoneNumberInput", "Error changing phone number", e)
            }
        },
        label = { Text(stringResource(R.string.phone_number)) },
        keyboardType = KeyboardType.Phone, // Set keyboard type for phone numbers
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for year input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onYearChange Callback function to handle year changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun YearInput(
    modifier: Modifier = Modifier,
    onYearChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var yearState by remember { mutableStateOf<String?>(null) } // State variable for year

    TextInput(
        modifier = modifier,
        value = yearState,
        onValueChange = { newYear ->
            try {
                yearState = newYear
                onYearChange(newYear) // Pass year to callback
            }
            catch (e: Exception) {
                Log.e("YearInput", "Error changing year", e)
            }
        },
        label = { Text(stringResource(R.string.year)) },
        keyboardType = KeyboardType.Number, // Set keyboard type for numbers
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for car make input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onMakeChange Callback function to handle car make changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun MakeInput(
    modifier: Modifier = Modifier,
    onMakeChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var makeState by remember { mutableStateOf<String?>(null) } // State variable for make
    TextInput(
        modifier = modifier,
        value = makeState,
        onValueChange = { newMake ->
            try {
                makeState = newMake
                onMakeChange(newMake)
            }
            catch (e: Exception) {
                Log.e("MakeInput", "Error changing make", e)
            }
        },
        label = { Text(stringResource(R.string.make)) },
        keyboardType = KeyboardType.Text, // Allow text input for make
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for car model input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onModelChange Callback function to handle car model changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun ModelInput(
    modifier: Modifier = Modifier,
    onModelChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var modelState by remember { mutableStateOf<String?>(null) } // State variable for model
    TextInput(
        modifier = modifier,
        value = modelState,
        onValueChange = { newModel ->
            try {
                modelState = newModel
                onModelChange(newModel)
            }
            catch (e: Exception) {
                Log.e("ModelInput", "Error changing model", e)
            }
        },
        label = { Text(stringResource(R.string.model)) },
        keyboardType = KeyboardType.Text, // Allow text input for model
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for license plate input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onLicensePlateChange Callback function to handle license plate changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun LicensePlateInput(
    modifier: Modifier = Modifier,
    onLicensePlateChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var licensePlate by remember { mutableStateOf("") } // State variable for license plate

    // Function to format license plate with uppercase letters and maximum of 7 characters
    fun formatLicensePlate(plate: String): String {
        return plate.take(7).uppercase() // Take maximum 7 characters, convert to uppercase
    }

    TextInput(
        modifier = modifier,
        value = licensePlate,
        onValueChange = { newPlate ->
            try {
                licensePlate = formatLicensePlate(newPlate)
                onLicensePlateChange(licensePlate) // Pass formatted plate to callback
            }
            catch (e: Exception) {
                Log.e("LicensePlateInput", "Error changing license plate", e)
            }
        },
        label = { Text(stringResource(R.string.license_plate)) },
        keyboardType = KeyboardType.Text, // Allow alphanumeric input
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for state input field.
 *
 * @param modifier Modifier for the TextInput.
 * @param onStateChange Callback function to handle state changes.
 * @param isError Flag indicating if there's an error in input validation.
 * @param errorMessage Error message to display if isError is true.
 */
@Composable
fun StateInput(
    modifier: Modifier = Modifier,
    onStateChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var state by remember { mutableStateOf("") } // State variable for state abbreviation

    TextInput(
        modifier = modifier,
        value = state,
        onValueChange = { newState ->
            try {
                state = newState.uppercase()
                    .take(2) // Take maximum 2 characters, convert to uppercase
                onStateChange(state) // Pass state abbreviation to callback
            }
            catch (e: Exception) {
                Log.e("StateInput", "Error changing state", e)
            }
        },
        label = { Text(stringResource(R.string.state)) },
        keyboardType = KeyboardType.Text, // Allow alphanumeric input
        isError = isError,
        errorMessage = errorMessage
    )
}

/**
 * Composable function for a registration button with validation feedback.
 *
 * @param modifier Modifier to be applied to the Button.
 * @param onClick Callback function to handle button clicks.
 * @param enabled Flag to control the button's enabled state (default is false).
 * @param validationFailed Flag to indicate if the validation has failed (default is false).
 * @param validationMessage Optional validation message to display when validation fails.
 */
@Composable
fun RegistrationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = false,
    validationFailed: Boolean = false, // Add a flag to indicate validation failure
    validationMessage: String? = null, // Add an optional validation message
) {
    Column {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { if (enabled) onClick() },
            enabled = enabled,
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(text = stringResource(R.string.register))
        }
        if (validationFailed && validationMessage != null) {
            Text(
                text = validationMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}