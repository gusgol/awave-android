package tech.hiregus.awave.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tech.hiregus.awave.createitinerary.CreateItineraryViewModel
import tech.hiregus.awave.createitinerary.SelectPlacesViewModel
import tech.hiregus.awave.createitinerary.source.DefaultPlacesRepository
import tech.hiregus.awave.createitinerary.source.PlacesDataSource
import tech.hiregus.awave.createitinerary.source.PlacesRepository
import tech.hiregus.awave.createitinerary.source.remote.PlacesRemoteDataSource
import tech.hiregus.awave.data.AppDatabase
import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.DB_NAME
import tech.hiregus.awave.data.PlacesService
import tech.hiregus.awave.data.api.providePlacesApi
import tech.hiregus.awave.home.HomeViewModel
import tech.hiregus.awave.itineraries.source.DefaultItinerariesRepository
import tech.hiregus.awave.itineraries.source.ItinerariesDataSource
import tech.hiregus.awave.itineraries.source.ItinerariesRepository
import tech.hiregus.awave.itineraries.source.local.ItinerariesLocalDataSource

val databaseModule = module {
    factory {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
}

val itinerariesModule = module {
    factory { get<AppDatabase>().itineraryDao() }
    factory<ItinerariesDataSource> { ItinerariesLocalDataSource(get()) }
    factory<ItinerariesRepository> { DefaultItinerariesRepository(get()) }
    viewModel { CreateItineraryViewModel(get()) }
}

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

val placesModule = module {
    factory { providePlacesApi().create(PlacesService::class.java) }
    factory<PlacesDataSource> { PlacesRemoteDataSource(get()) }
    factory<PlacesRepository> { DefaultPlacesRepository(get()) }
    viewModel { (city: City?) -> SelectPlacesViewModel(city, get()) }
}
