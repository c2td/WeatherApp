package com.pm.weatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast_table")
data class Forecast(

    @PrimaryKey
    @SerializedName("date")
    var date: String,

    @Embedded(prefix = "day_")
    @SerializedName("day")
    var day: Day,

    @Embedded(prefix = "night_")
    @SerializedName("night")
    var night: Night
)