package com.pm.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Day(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("phenomenon")
    var phenomenon: String,

    @SerializedName("tempmin")
    var tempMin: Int,

    @SerializedName("tempmax")
    var tempMax: Int,

    @SerializedName("text")
    var description: String,

    @SerializedName("sea")
    var sea: String?,

    @SerializedName("peipsi")
    var peipsi: String?,

    @SerializedName("places")
    var places: ArrayList<Place?>?,

    @SerializedName("winds")
    val winds: ArrayList<Wind?>?
)
