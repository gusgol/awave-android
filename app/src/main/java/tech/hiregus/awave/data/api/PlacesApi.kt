package tech.hiregus.awave.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PlacesApi {
    const val HOST = "https://api.foursquare.com/v2/"
    const val CLIENT_ID = "Add yours"
    const val CLIENT_SECRET = "Add yours"
    const val V = "20200618"
}


fun providePlacesApi(): Retrofit {
    val client = OkHttpClient.Builder().apply {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        addInterceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().apply {
                addQueryParameter("client_id", PlacesApi.CLIENT_ID)
                addQueryParameter("client_secret", PlacesApi.CLIENT_SECRET)
                addQueryParameter("v", PlacesApi.V)
            }.build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
    return Retrofit.Builder()
        .baseUrl(PlacesApi.HOST)
        .client(client.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}