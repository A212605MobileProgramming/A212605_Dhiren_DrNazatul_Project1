package com.example.a212605_dhiren_drnazatul_project1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a212605_dhiren_drnazatul_project1.Screen
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealUiState
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel
import com.example.a212605_dhiren_drnazatul_project1.model.FoodDonation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationBoardScreen(navController: NavController, viewModel: CheapMealViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Donation Board") },
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.DonationForm.route) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Donation", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        bottomBar = { BottomNavBar(navController, viewModel) }
    ) { innerPadding ->
        if (uiState.donations.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🍱", style = MaterialTheme.typography.displayMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("No donations yet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("Tap + to share food with others", style = MaterialTheme.typography.bodySmall)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.donations.sortedBy { it.isTaken }, key = { it.id }) { donation ->
                    DonationCard(donation, info = uiState, onToggleTaken = { viewModel.toggleDonationTaken(donation.id) })
                }
            }
        }
    }
}

@Composable
fun DonationCard(donation: FoodDonation, info: CheapMealUiState, onToggleTaken: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (donation.isTaken) 0.55f else 1f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🍱", style = MaterialTheme.typography.titleLarge)
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(donation.foodItem, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                    Text("Qty: ${donation.quantity}", style = MaterialTheme.typography.labelSmall)
                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (donation.isTaken) MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    else MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Text(
                        text = if (donation.isTaken) "Taken" else "✓ Available",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (donation.isTaken) MaterialTheme.colorScheme.onSurfaceVariant
                        else MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("📍 ${donation.location}", style = MaterialTheme.typography.bodySmall)
                    Text("📞 ${info.contact}", style = MaterialTheme.typography.bodySmall)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("🗓 Expiry: ${donation.expiryDate}", style = MaterialTheme.typography.bodySmall)
                    if (donation.description.isNotBlank()) {
                        Text(
                            "📝 ${donation.description}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                onClick = onToggleTaken,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (donation.isTaken) MaterialTheme.colorScheme.onSurfaceVariant
                    else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (donation.isTaken) "Taken — Undo" else "Mark as Taken",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
