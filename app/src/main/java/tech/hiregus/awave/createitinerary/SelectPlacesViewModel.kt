package tech.hiregus.awave.createitinerary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.hiregus.awave.createitinerary.source.PlacesRepository
import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.Result
import timber.log.Timber

class SelectPlacesViewModel(
    val city: City?,
    private val repository: PlacesRepository
) : ViewModel() {

    init {
        Timber.plant(Timber.DebugTree())
        getPlaces()
    }

    private fun getPlaces() {
        if (city == null) return
        viewModelScope.launch {
            val result = repository.getPlaces(city)
            if (result is Result.Success) {
                // flow
            } else if (result is Result.Error){
                //
            }
        }
    }
}