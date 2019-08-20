package com.davidmag.movienatic.rest

import com.google.gson.annotations.SerializedName
import java.util.*

data class Dates (
    @SerializedName("maximum")
    val maximum : Date,
    @SerializedName("minimum")
    val minimum : Date
)