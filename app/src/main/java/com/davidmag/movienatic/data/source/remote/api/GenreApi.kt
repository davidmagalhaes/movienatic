package com.davidmag.movienatic.data.source.remote.api

import com.davidmag.movienatic.data.source.remote.dto.GenreResponse
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {
    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Maybe<GenreResponse>
}