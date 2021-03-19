package tech.hiregus.awave.data

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.local.ItinerariesDao

@Database(entities = [Itinerary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itineraryDao(): ItinerariesDao
}

const val DB_NAME = "awave.db"