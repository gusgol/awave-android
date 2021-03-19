package tech.hiregus.awave.data

import androidx.room.TypeConverter

class CityConverters {

    @TypeConverter
    fun stringToCity(cityId: String): City {
        return City.valueOf(cityId)
    }

    @TypeConverter
    fun cityToString(city: City): String {
        return city.name
    }

}