package com.davidmag.movienatic.data.source.remote.api

import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.data.source.remote.dto.LookupMoviesResponse
import com.davidmag.movienatic.data.source.remote.dto.MovieObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie")
    fun search(@Query("api_key")              apiKey : String,
                     @Query("query")                query : String,
                     @Query("include_adult")        includeAdult : Boolean? = null,
                     @Query("year")                 year : Int? = null,
                     @Query("primary_release_year") primaryReleaseYear : Int? = null,
                     @Query("page")                 page : Int? = null,
                     @Query("language")             language : String? = null,
                     @Query("region")               region : String? = null
    ) : Call<LookupMoviesResponse>

    @GET("movie/{movie_id}")
    fun findById(@Path("movie_id")   movie_id : String,
                 @Query("api_key")    apiKey : String
    ) : Call<MovieObject>

    @GET("discover/movie")
    fun lookup(@Query("api_key")      apiKey  : String,
                     @Query("with_genres")  genreIds : String
    ) : Call<LookupMoviesResponse>
}