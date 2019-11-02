package com.davidmag.movienatic.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import java.io.Serializable

open class MovieObject : Serializable {
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("poster_path")
    var posterPath : String? = null
    @SerializedName("release_date")
    var releaseDate : LocalDate? = null
    @SerializedName("genres")
    var genres : List<GenreObject>? = null
    @SerializedName("backdrop_path")
    var backdropPath : String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("overview")
    var overview: String? = null

    var lastUpdate : LocalDate? = null
}

