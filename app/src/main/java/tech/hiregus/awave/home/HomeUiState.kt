package tech.hiregus.awave.home

import androidx.annotation.StringRes
import tech.hiregus.awave.itineraries.Itinerary

sealed class HomeUiState {
    object LoadingState : HomeUiState()
    object EmptyState : HomeUiState()
    data class DataState(val data: List<Itinerary>) : HomeUiState()
    data class ErrorState(@StringRes val resource: Int) : HomeUiState()
}