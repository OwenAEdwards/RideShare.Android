package com.example.androidproject

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidproject.ui.theme.AndroidProjectTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivityContent() {
        // Set up the test environment by setting the content of MainActivity
        composeTestRule.setContent {
            AndroidProjectTheme {
                // Create a modifier with a test tag for identifying the RegistrationComposable
                val modifier = Modifier.testTag("RegistrationComposable")
                // Create and display the RegistrationComposable
                RegistrationComposable(modifier)
            }
        }

        // Perform UI assertions on the RegistrationComposable
        composeTestRule.onNodeWithTag("RegistrationComposable")
            .assertExists() // Verify that the RegistrationComposable exists in the UI
            .assertHasClickAction() // Verify that the registration button has a click action
    }

    // You can add more tests as needed to test specific interactions or behaviors
}
