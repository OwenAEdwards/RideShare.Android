package com.example.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidproject.ui.theme.AndroidProjectTheme

// MainActivity serves as the entry point for the application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge layout for immersive UI experience
        enableEdgeToEdge()
        setContent {
            AndroidProjectTheme {
                // Scaffold provides a layout structure for the app's content
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // RegistrationComposable contains the registration screen UI
                    RegistrationComposable(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Composable function for the registration screen UI
@Composable
fun RegistrationComposable(modifier: Modifier = Modifier) {
    RegistrationScreen(
        validator = RegistrationInputValidator(),
        modifier = modifier
    )
}
