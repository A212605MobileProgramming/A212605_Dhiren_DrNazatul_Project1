package com.example.a212605_dhiren_drnazatul_project1.model

data class CheapMealUiState(
    val name: String = "",
    val email: String = "",
    val contact: String = "",
    val budgetPerMeal: String = "",
    val dietaryPreference: String = "",
    val selectedCategory: String = "All",
    val donations: List<FoodDonation> = emptyList()
)
data class FoodDonation(
    val id: String = java.util.UUID.randomUUID().toString(),
    val foodItem: String,
    val quantity: String,
    val location: String,
    val expiryDate: String,
    val description: String = "",
    val isTaken: Boolean = false
)

