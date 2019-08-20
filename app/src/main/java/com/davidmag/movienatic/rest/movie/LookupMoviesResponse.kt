package com.davidmag.movienatic.rest.movie

import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.Dates
import com.google.gson.annotations.SerializedName

data class LookupMoviesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Movie>,
    @SerializedName("dates")
    val dates : Dates,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int
)