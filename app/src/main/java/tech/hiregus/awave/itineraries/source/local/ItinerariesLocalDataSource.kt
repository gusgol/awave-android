package tech.hiregus.awave.itineraries.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.ItinerariesDataSource

class ItinerariesLocalDataSource(
    private val itinerariesDao: ItinerariesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ItinerariesDataSource {

    override suspend fun getItineraries(): Result<List<Itinerary>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(itinerariesDao.getAll())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}

