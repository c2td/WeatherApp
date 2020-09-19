package com.pm.weatherapp.db

import android.content.Context
import androidx.room.*
import com.pm.weatherapp.model.Day
import com.pm.weatherapp.model.Forecast
import com.pm.weatherapp.model.Night
import com.pm.weatherapp.model.Place

/**
 * The database for caching the forecasts data
 */
@Database(entities = [Forecast::class, Day::class, Night::class, Place::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val forecastDao: ForecastDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "forecasts").build()
        }
    }
    return INSTANCE
}
