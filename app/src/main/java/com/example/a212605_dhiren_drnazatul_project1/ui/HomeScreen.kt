package com.example.a212605_dhiren_drnazatul_project1.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a212605_dhiren_drnazatul_project1.Screen
import com.example.a212605_dhiren_drnazatul_project1.datasource.DataSource
import com.example.a212605_dhiren_drnazatul_project1.model.FlashDeal
import com.example.a212605_dhiren_drnazatul_project1.model.FoodCategory
import com.example.a212605_dhiren_drnazatul_project1.model.FoodDeal
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: CheapMealViewModel) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navController, viewModel) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            SearchBar()
            SavingsTrackerCard()
            FlashDealsRow(DataSource.flashDeals)
            PromoBanner()
            CategoriesSection(DataSource.categories)
            BudgetPicksSection(DataSource.budgetPicks)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "📍 WQ9C+2H, 43600 Bangi, Selangor",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "CheapMeal — Save More, Eat Well 🍛",
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search cheap eats near you") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    }
}

@Composable
fun SavingsTrackerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("💰 Your Savings This Month", style = MaterialTheme.typography.labelMedium)
                Text("RM 47.30 saved!", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.headlineMedium)
                Text("You're on a budget streak 🔥", style = MaterialTheme.typography.labelSmall)
            }
            Icon(Icons.Default.Star, null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(36.dp))
        }
    }
}

@Composable
fun FlashDealsRow(flashDeals: List<FlashDeal>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text("⚡ Flash Deals", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            flashDeals.forEach { deal ->
                FlashDealChip(deal.emoji, deal.label)
            }
        }
    }
}

@Composable
fun FlashDealChip(emoji: String, label: String) {
    Card(
        modifier = Modifier.size(64.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            Text(emoji, fontSize = 18.sp)
            Text(label, style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PromoBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .height(70.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("BUDGET MEALS UNDER RM10!", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun CategoriesSection(categories: List<FoodCategory>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text("Categories", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Row(Modifier.fillMaxWidth().padding(top = 4.dp), Arrangement.SpaceAround) {
            categories.forEach { category ->
                CategoryItem(category.emoji, category.label)
            }
        }
    }
}

@Composable
fun CategoryItem(emoji: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(Modifier.size(50.dp), shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.tertiaryContainer) {
            Box(contentAlignment = Alignment.Center) { Text(emoji, fontSize = 22.sp) }
        }
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun BudgetPicksSection(budgetPicks: List<FoodDeal>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text("Budget Picks 🏷️", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        budgetPicks.forEach { deal ->
            ExpandableFoodCard(
                emoji = deal.emoji,
                name = deal.name,
                price = deal.price,
                oldPrice = deal.oldPrice,
                discount = deal.discount,
                location = deal.location,
                time = deal.time,
                detail = deal.detail
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun ExpandableFoodCard(
    emoji: String, name: String, price: String, oldPrice: String,
    discount: String, location: String, time: String, detail: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(Modifier.size(56.dp), shape = RoundedCornerShape(8.dp)) {
                    Box(contentAlignment = Alignment.Center) { Text(emoji, fontSize = 24.sp) }
                }
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(name, fontWeight = FontWeight.Bold)
                    Text(price, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("📍 $location", style = MaterialTheme.typography.labelSmall)
                }
                Icon(if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, null)
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(detail, style = MaterialTheme.typography.bodySmall)
                    Text("🕐 Available: $time", style = MaterialTheme.typography.labelSmall)
                    Text("Was $oldPrice  •  $discount", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController, viewModel: CheapMealViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Deals.route,
            onClick = { navController.navigate(Screen.Deals.route) },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Deals") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Profile.route || currentRoute == Screen.ProfileSetup.route,
            onClick = {
                if (viewModel.isProfileSet) navController.navigate(Screen.Profile.route)
                else navController.navigate(Screen.ProfileSetup.route)
            },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Account") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.DonationBoard.route || currentRoute == Screen.DonationForm.route,
            onClick = { navController.navigate(Screen.DonationBoard.route) },
            icon = { Icon(Icons.Default.Favorite, null) },
            label = { Text("Donate") }
        )
    }
}
