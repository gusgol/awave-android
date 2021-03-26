package tech.hiregus.awave.createitinerary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.ItinerariesRepository

class CreateItineraryViewModel(private val itinerariesRepository: ItinerariesRepository) : ViewModel() {

    fun saveItinerary(title: String) {
        viewModelScope.launch {
            itinerariesRepository.saveItinerary(Itinerary(title = title))
        }
    }

}