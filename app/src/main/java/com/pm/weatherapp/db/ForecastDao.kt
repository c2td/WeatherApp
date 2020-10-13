package com.pm.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.pm.weatherapp.model.Forecast

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast_table")
    fun getForecasts(): LiveData<List<Forecast>>

    @Insert(onConflict = REPLACE)
    fun insertForecasts(forecasts: List<Forecast>)

    @Query("DELETE FROM forecast_table")
    fun deleteAll()

}