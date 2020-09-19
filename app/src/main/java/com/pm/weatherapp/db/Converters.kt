package com.pm.weatherapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pm.weatherapp.model.Place
import com.pm.weatherapp.model.Wind
import java.lang.reflect.Type

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromPlacesList(places: ArrayList<Place?>?): String? {
        if (places == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Place?>?>() {}.type
        return Gson().toJson(places, type)
    }

    @TypeConverter
    @JvmStatic
    fun toPlacesList(placesString: String?): ArrayList<Place>? {
        if (placesString == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Place?>?>() {}.type
        return Gson().fromJson<ArrayList<Place>>(placesString, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromWindsList(places: ArrayList<Wind?>?): String? {
        if (places == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Wind?>?>() {}.type
        return Gson().toJson(places, type)
    }

    @TypeConverter
    @JvmStatic
    fun toWindsList(placesString: String?): ArrayList<Wind>? {
        if (placesString == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Wind?>?>() {}.type
        return Gson().fromJson<ArrayList<Wind>>(placesString, type)
    }
}