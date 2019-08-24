package com.davidmag.movienatic.rest.api

import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.response.LookupMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key")  apiKey : String,
                          @Query("page")     page : Int?,
                          @Query("language") language : String?,
                          @Query("region")   region : String?
    ) : Call<LookupMoviesResponse>

    @GET("search/movie")
    fun searchMovies(@Query("api_key")              apiKey : String,
                     @Query("query")                query : String,
                     @Query("include_adult")        includeAdult : Boolean?,
                     @Query("year")                 year : Int?,
                     @Query("primary_release_year") primaryReleaseYear : Int?,
                     @Query("page")                 page : Int?,
                     @Query("language")             language : String?,
                     @Query("region")               region : String?
    ) : Call<LookupMoviesResponse>

    @GET("movie/{movie_id}")
    fun findMovie(@Path("movie_id")    movie_id : Int,
                  @Query("api_key")    apiKey : String

    ) : Call<Movie>
}