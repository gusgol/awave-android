package tech.hiregus.awave.itineraries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tech.hiregus.awave.data.City
import java.util.*

@Entity(tableName = "itineraries")
data class Itinerary(@PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
                     @ColumnInfo(name = "title") val title: String = "",
                     @ColumnInfo(name = "city") val city: City = City.Paris)
