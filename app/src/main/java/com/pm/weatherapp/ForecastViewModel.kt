package com.pm.weatherapp

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.pm.weatherapp.db.getDatabase
import kotlinx.coroutines.launch
import java.io.IOException

class ForecastViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(getDatabase(application))

    init {
        refreshDataFromRepository()
    }

    val forecasts = repository.forecasts

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshVideos()

            } catch (networkError: IOException) {
                Toast.makeText(getApplication(), R.string.network_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Factory for ViewModel
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ForecastViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}