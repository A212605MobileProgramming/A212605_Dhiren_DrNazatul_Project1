package com.example.a212605_dhiren_drnazatul_project1.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CheapMealViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CheapMealUiState())
    val uiState: StateFlow<CheapMealUiState> = _uiState.asStateFlow()

    val isProfileSet: Boolean
        get() = _uiState.value.name.isNotEmpty()

    fun updateProfile(name: String, email: String,contact: String, budgetPerMeal: String, dietaryPreference: String) {
        _uiState.update {
            it.copy(
                name = name,
                email = email,
                contact = contact,
                budgetPerMeal = budgetPerMeal,
                dietaryPreference = dietaryPreference
            )
        }
    }

    fun setCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun resetProfile() {
        _uiState.update { it.copy(name = "", email = "",contact = "", budgetPerMeal = "", dietaryPreference = "") }
    }

    fun addDonation(donation: FoodDonation) {
        _uiState.update { it.copy(donations = it.donations + donation) }
    }


    fun toggleDonationTaken(id: String) {
        _uiState.update { state ->
            state.copy(
                donations = state.donations.map { d ->
                    if (d.id == id) d.copy(isTaken = !d.isTaken) else d
                }
            )
        }
    }
}
