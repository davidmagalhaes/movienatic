package com.davidmag.movienatic.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class LookupMoviesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<MovieObject>,
    @SerializedName("dates")
    val dates : Dates,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int
)