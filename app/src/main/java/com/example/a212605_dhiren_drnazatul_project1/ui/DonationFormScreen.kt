package com.example.a212605_dhiren_drnazatul_project1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel
import com.example.a212605_dhiren_drnazatul_project1.model.FoodDonation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationFormScreen(navController: NavController, viewModel: CheapMealViewModel) {
    var foodItem by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Donation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Share food with those in need", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)

            OutlinedTextField(
                value = foodItem,
                onValueChange = { foodItem = it },
                label = { Text("Food Item") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity (e.g. 5 packs)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Pickup Location") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = expiryDate,
                onValueChange = { expiryDate = it },
                label = { Text("Best Before / Expiry Date") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Additional Notes (optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )

            Spacer(Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        if (foodItem.isNotBlank() && quantity.isNotBlank()
                            && location.isNotBlank() && expiryDate.isNotBlank()
                        ) {
                            viewModel.addDonation(
                                FoodDonation(
                                    foodItem = foodItem.trim(),
                                    quantity = quantity.trim(),
                                    location = location.trim(),
                                    expiryDate = expiryDate.trim(),
                                    description = description.trim()
                                )
                            )
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
