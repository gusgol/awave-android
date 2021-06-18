package tech.hiregus.awave.data

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {

    @GET("venues/search")
    suspend fun getPlaces(@Query("near") near: String): ResponseBody

}