package tech.hiregus.awave.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.hiregus.awave.R
import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.source.ItinerariesRepository
import tech.hiregus.awave.home.HomeUiState.*
import tech.hiregus.awave.itineraries.Itinerary

class HomeViewModel(private val itinerariesRepository: ItinerariesRepository) : ViewModel() {

    private val _uiState = MutableLiveData<HomeUiState>()
    val uiState: LiveData<HomeUiState>
        get() = _uiState

    init {
        getItineraries()
    }

    fun saveItinerary() {
        viewModelScope.launch {
            itinerariesRepository.saveItinerary(Itinerary(title = "Day 1 in Paris"))
        }
    }

    private fun getItineraries() {
        viewModelScope.launch {
            _uiState.value = LoadingState
            val result = itinerariesRepository.getItineraries()
            if (result is Result.Success) {
                val itineraries = result.data
                if (itineraries.isEmpty()) {
                    _uiState.value = EmptyState
                } else {
                    _uiState.value = DataState(itineraries)
                }
            } else {
                _uiState.value = ErrorState(R.string.error_load_itineraries)
            }
        }
    }

}