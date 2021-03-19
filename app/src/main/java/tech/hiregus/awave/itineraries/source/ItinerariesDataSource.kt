package tech.hiregus.awave.itineraries.source

import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary

interface ItinerariesDataSource {

    suspend fun getItineraries(): Result<List<Itinerary>>

    suspend fun getItinerary(id: String): Result<Itinerary>

    suspend fun saveItinerary(itinerary: Itinerary)

    suspend fun updateItinerary(itinerary: Itinerary): Result<Boolean>

    suspend fun deleteItinerary(itinerary: Itinerary)

}