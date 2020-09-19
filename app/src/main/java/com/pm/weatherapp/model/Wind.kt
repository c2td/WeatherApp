package com.pm.weatherapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Wind(
    @PrimaryKey
    @SerializedName("name")
    var name: String,

    @SerializedName("direction")
    var direction: String,

    @SerializedName("speedpmin")
    var speedMin: Double,

    @SerializedName("speedmax")
    var speedMax: Double
)