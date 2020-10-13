package com.pm.weatherapp

import androidx.lifecycle.LiveData
import com.pm.weatherapp.api.ForecastNetwork
import com.pm.weatherapp.db.AppDatabase
import com.pm.weatherapp.model.Forecast

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching forecasts data from the network and storing them on disk
 */
class Repository(private val database: AppDatabase) {

    val forecasts: LiveData<List<Forecast>> = database.forecastDao.getForecasts()

    // make the web request and update the database
    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val response = ForecastNetwork.apiService.getForecasts()
            // delete the previous data
            database.forecastDao.deleteAll()
            database.forecastDao.insertForecasts(response.forecasts)
        }
    }
}