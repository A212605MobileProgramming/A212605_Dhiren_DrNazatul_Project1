package com.example.a212605_dhiren_drnazatul_project1

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a212605_dhiren_drnazatul_project1.model.CheapMealViewModel
import com.example.a212605_dhiren_drnazatul_project1.ui.DealsScreen
import com.example.a212605_dhiren_drnazatul_project1.ui.DonationBoardScreen
import com.example.a212605_dhiren_drnazatul_project1.ui.DonationFormScreen
import com.example.a212605_dhiren_drnazatul_project1.ui.HomeScreen
import com.example.a212605_dhiren_drnazatul_project1.ui.ProfileScreen
import com.example.a212605_dhiren_drnazatul_project1.ui.ProfileSetupScreen

sealed class Screen(val route: String) {
    object Home           : Screen("home")
    object Deals          : Screen("deals")
    object ProfileSetup   : Screen("profile_setup")
    object Profile        : Screen("profile")
    object DonationBoard  : Screen("donation_board")
    object DonationForm   : Screen("donation_form")
}

@Composable
fun CheapMealApp() {
    val navController = rememberNavController()
    val viewModel = viewModel<CheapMealViewModel>()

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(Screen.Deals.route) {
            DealsScreen(navController, viewModel)
        }
        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(navController, viewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController, viewModel)
        }
        composable(Screen.DonationBoard.route) {
            DonationBoardScreen(navController, viewModel)
        }
        composable(Screen.DonationForm.route) {
            DonationFormScreen(navController, viewModel)
        }
    }
}
