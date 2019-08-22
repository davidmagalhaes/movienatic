package com.davidmag.movienatic.rest.api

import com.davidmag.movienatic.rest.response.ConfigurationsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigurationsApi {
    @GET("configuration")
    fun updateConfigurations(@Query("api_key")  apiKey : String): Call<ConfigurationsApiResponse>
}