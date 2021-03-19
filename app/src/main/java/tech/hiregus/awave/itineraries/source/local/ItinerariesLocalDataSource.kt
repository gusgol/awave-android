package tech.hiregus.awave.itineraries.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.ItinerariesDataSource
import timber.log.Timber
import java.io.IOException

class ItinerariesLocalDataSource(
    private val itinerariesDao: ItinerariesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ItinerariesDataSource {

    init {
        Timber.plant(Timber.DebugTree())
    }

    override suspend fun getItineraries(): Result<List<Itinerary>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(itinerariesDao.getAll())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getItinerary(id: String): Result<Itinerary> = withContext(ioDispatcher) {
        try {
            val itinerary = itinerariesDao.get(id)
            if (itinerary != null) {
                return@withContext Result.Success(itinerary)
            } else {
                return@withContext Result.Error(IOException("No itinerary found"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveItinerary(itinerary: Itinerary) = withContext(ioDispatcher) {
        itinerariesDao.insert(itinerary)
        Timber.d("Itinerary ${itinerary.title} saved")
    }

    override suspend fun updateItinerary(itinerary: Itinerary): Result<Boolean> = withContext(ioDispatcher){
        try {
            val updated = itinerariesDao.update(itinerary)
            if (updated == 1) {
                return@withContext Result.Success(true)
            } else {
                return@withContext Result.Error(IOException("Unable to update the itinerary"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteItinerary(itinerary: Itinerary) {
        itinerariesDao.delete(itinerary)
        Timber.d("Itinerary ${itinerary.title} deleted")
    }

}

