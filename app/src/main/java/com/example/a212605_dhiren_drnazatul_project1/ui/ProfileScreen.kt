package com.example.a212605_dhiren_drnazatul_project1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel
import com.example.a212605_dhiren_drnazatul_project1.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: CheapMealViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Account", fontWeight = FontWeight.Bold) },
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            Surface(
                modifier = Modifier.size(90.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = uiState.name.take(1).uppercase().ifEmpty { "?" },
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Text(uiState.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(uiState.email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(Modifier.height(8.dp))

            ProfileInfoCard(
                icon = Icons.Default.ShoppingCart,
                label = "Budget Per Meal",
                value = uiState.budgetPerMeal
            )
            ProfileInfoCard(
                icon = Icons.Default.Favorite,
                label = "Dietary Preference",
                value = uiState.dietaryPreference
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Total Saved This Month", style = MaterialTheme.typography.labelMedium)
                        Text("RM 47.30 🔥", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleLarge)
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Screen.ProfileSetup.route) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Edit Profile", fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = {
                    viewModel.resetProfile()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Log Out", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ProfileInfoCard(icon: ImageVector, label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(12.dp))
            Column {
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(value.ifEmpty { "—" }, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
