package tech.hiregus.awave.itineraries.source

import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary

class DefaultItinerariesRepository(
    private val localDataSource: ItinerariesDataSource
) : ItinerariesRepository {

    override suspend fun getItineraries(): Result<List<Itinerary>> {
        return localDataSource.getItineraries()
    }

}