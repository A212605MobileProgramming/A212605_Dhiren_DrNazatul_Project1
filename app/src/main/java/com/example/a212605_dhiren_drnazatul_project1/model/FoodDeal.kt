package com.example.a212605_dhiren_drnazatul_project1.model

data class FoodDeal(
    val emoji: String,
    val name: String,
    val price: String,
    val oldPrice: String,
    val discount: String,
    val location: String,
    val time: String,
    val detail: String,
    val category: String = "All"
)

data class FlashDeal(
    val emoji: String,
    val label: String
)

data class FoodCategory(
    val emoji: String,
    val label: String
)
