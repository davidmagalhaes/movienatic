package com.davidmag.movienatic.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreObject (
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("name")
    var name : String? = null
)