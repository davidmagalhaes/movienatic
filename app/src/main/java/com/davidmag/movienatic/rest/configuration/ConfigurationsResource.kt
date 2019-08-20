package com.davidmag.movienatic.rest.configuration

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigurationsResource {
    @GET("configuration")
    fun updateConfigurations(@Query("api_key")  apiKey : String): Call<ConfigurationsResponse>
}