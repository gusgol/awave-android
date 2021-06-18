package tech.hiregus.awave.createitinerary.source

import tech.hiregus.awave.createitinerary.source.PlacesDataSource
import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.Result

class DefaultPlacesRepository(
    private val remoteDataSource: PlacesDataSource
) : PlacesRepository {

    override suspend fun getPlaces(city: City): Result<String> = remoteDataSource.getPlaces(city)

}