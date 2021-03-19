package tech.hiregus.awave.itineraries.source

import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary

interface ItinerariesDataSource {

    suspend fun getItineraries(): Result<List<Itinerary>>

}