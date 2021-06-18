package tech.hiregus.awave.createitinerary.source.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.hiregus.awave.createitinerary.source.PlacesDataSource
import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.PlacesService
import tech.hiregus.awave.data.Result

class PlacesRemoteDataSource(
    private val placesService: PlacesService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlacesDataSource {

    override suspend fun getPlaces(city: City): Result<String> = withContext(ioDispatcher) {
        return@withContext try {
            val response = placesService.getPlaces(city.name)
            Result.Success(response.string())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}