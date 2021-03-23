package tech.hiregus.awave.itineraries.source

import androidx.annotation.VisibleForTesting
import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary
import java.lang.Exception
import java.util.LinkedHashMap

class FakeItinerariesRepository : ItinerariesRepository {

    private var shouldReturnError = false

    var itinerariesServiceData: LinkedHashMap<String, Itinerary> = LinkedHashMap()

    override suspend fun getItineraries(): Result<List<Itinerary>> {
        if (shouldReturnError) {
            return Result.Error(Exception("Test exception"))
        }
        return Result.Success(itinerariesServiceData.values.toList())
    }

    override suspend fun getItinerary(id: String): Result<Itinerary> {
        if (shouldReturnError) {
            return Result.Error(Exception("Test exception"))
        }
        itinerariesServiceData[id]?.let {
            return Result.Success(it)
        }
        return Result.Error(Exception("Could not find task"))
    }

    override suspend fun saveItinerary(itinerary: Itinerary) {
        itinerariesServiceData[itinerary.id] = itinerary
    }

    override suspend fun updateItinerary(itinerary: Itinerary): Result<Boolean> {
        itinerariesServiceData[itinerary.id] = itinerary
        return Result.Success(true)
    }

    override suspend fun deleteItinerary(itinerary: Itinerary) {
        itinerariesServiceData.remove(itinerary.id)
    }

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    @VisibleForTesting
    fun addItineraries(vararg itineraries: Itinerary) {
        for (itinerary in itineraries) {
            itinerariesServiceData[itinerary.id] = itinerary
        }
    }

}