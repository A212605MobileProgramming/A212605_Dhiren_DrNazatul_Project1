package com.example.a212605_dhiren_drnazatul_project1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel
import com.example.a212605_dhiren_drnazatul_project1.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(navController: NavController, viewModel: CheapMealViewModel) {
    val current = viewModel.uiState.value
    var name by remember { mutableStateOf(current.name) }
    var email by remember { mutableStateOf(current.email) }
    var contact by remember { mutableStateOf(current.contact) }
    var budget by remember { mutableStateOf(current.budgetPerMeal) }
    var dietary by remember { mutableStateOf(current.dietaryPreference) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Set Up Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { BottomNavBar(navController, viewModel) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = contact,
                onValueChange = { contact = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = budget,
                onValueChange = { budget = it },
                label = { Text("Budget Per Meal (e.g. RM 10)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = dietary,
                onValueChange = { dietary = it },
                label = { Text("Dietary Preference (e.g. Halal, Vegan)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.updateProfile(name, email, contact,budget, dietary)
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.ProfileSetup.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = name.isNotBlank()
            ) {
                Text("Save Profile", fontWeight = FontWeight.Bold)
            }
        }
    }
}
