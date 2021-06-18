package tech.hiregus.awave.createitinerary.source

import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.Result

interface PlacesDataSource {

    suspend fun getPlaces(city: City): Result<String>

}