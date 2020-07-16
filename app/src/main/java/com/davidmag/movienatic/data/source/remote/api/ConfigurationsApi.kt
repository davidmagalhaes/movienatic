package com.davidmag.movienatic.data.source.remote.api

import com.davidmag.movienatic.data.source.remote.dto.ConfigurationsApiResponse
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigurationsApi {
    @GET("configuration")
    fun updateConfigurations(
        @Query("api_key")  apiKey : String
    ): Maybe<ConfigurationsApiResponse>
}