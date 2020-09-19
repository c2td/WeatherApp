package com.pm.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Place(

    @PrimaryKey
    @SerializedName("name")
    var name: String,

    @SerializedName("phenomenon")
    var phenomenon: String,

    @SerializedName("tempmin")
    var tempMin: Int?,

    @SerializedName("tempmax")
    var tempMax: Int?
)