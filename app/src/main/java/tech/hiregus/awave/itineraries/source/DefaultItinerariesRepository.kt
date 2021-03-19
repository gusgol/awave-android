package tech.hiregus.awave.itineraries.source

import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary

class DefaultItinerariesRepository(
    private val localDataSource: ItinerariesDataSource
) : ItinerariesRepository {

    override suspend fun getItineraries(): Result<List<Itinerary>> {
        return localDataSource.getItineraries()
    }

    override suspend fun getItinerary(id: String): Result<Itinerary> {
        return localDataSource.getItinerary(id)
    }

    override suspend fun saveItinerary(itinerary: Itinerary) {
        localDataSource.saveItinerary(itinerary)
    }

    override suspend fun updateItinerary(itinerary: Itinerary): Result<Boolean> {
        return localDataSource.updateItinerary(itinerary)
    }

    override suspend fun deleteItinerary(itinerary: Itinerary) {
        localDataSource.deleteItinerary(itinerary)
    }

}