package tech.hiregus.awave.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.local.ItinerariesDao

@Database(entities = [Itinerary::class], version = 1)
@TypeConverters(value = [CityConverters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun itineraryDao(): ItinerariesDao
}

const val DB_NAME = "awave.db"