package com.example.runmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.runmate.ui.theme.RunMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isProfileScreenVisible by remember { mutableStateOf(false) }
    var isWorkoutsScreenVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Personal statistics
        PersonalStatistics()

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons for navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    // Navigate to the profile screen
                    isProfileScreenVisible = true
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Profile")
            }

            Button(
                onClick = {
                    // Navigate to the workouts screen
                    isWorkoutsScreenVisible = true
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("Workouts")
            }
        }

        // Navigate to the profile screen
        if (isProfileScreenVisible) {
            ProfileScreen(onNavigateBack = { isProfileScreenVisible = false })
        }

        // Navigate to the workouts screen
        if (isWorkoutsScreenVisible) {
            WorkoutsScreen(onNavigateBack = { isWorkoutsScreenVisible = false })
        }
    }
}

@Composable
fun PersonalStatistics() {
    // TODO: Implement personal statistics UI
}

@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    // TODO: Implement profile screen UI
}

@Composable
fun WorkoutsScreen(onNavigateBack: () -> Unit) {
    // TODO: Implement workouts screen UI
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RunMateTheme {
        MainScreen()
    }
}
