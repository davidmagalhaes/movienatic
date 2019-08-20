package com.davidmag.movienatic.rest.movie

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.util.ApiUtils
import kotlinx.coroutines.Deferred

object MovieResourceClient {
    val movies = MutableLiveData<List<Movie>>()

    private val movieResource by lazy {
        App.retrofit.create(MovieResource::class.java)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>> {
        val call = movieResource.getUpcomingMovies(BuildConfig.API_KEY, page, language, region)

        return ApiUtils.doRequest(call){
            val results = it.body()!!.results
            movies.postValue(results)
            results
        }
    }

    fun searchMovies(query : String, includeAdult : Boolean? = null, year : Int? = null, primaryReleaseYear : Int? = null,
                     page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>> {

        val call = movieResource.searchMovies(BuildConfig.API_KEY, query, includeAdult, year, primaryReleaseYear, page, language, region)

        return ApiUtils.doRequest(call){
            val results = it.body()!!.results
            movies.postValue(results)
            results
        }
    }
}