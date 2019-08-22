package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.api.MovieApi
import com.davidmag.movienatic.rest.response.LookupMoviesResponse
import io.realm.Realm
import retrofit2.Call

object MovieRepository {
    val movieApi : MovieApi by lazy {
        App.retrofit.create(MovieApi::class.java)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRealmListResource<Movie, LookupMoviesResponse>(Movie::class.java){
            override fun shouldFetch(item: List<Movie>): Boolean {
                return true
            }
            override fun createCall(): Call<LookupMoviesResponse> {
                return movieApi.getUpcomingMovies(BuildConfig.API_KEY, page, language, region)
            }
            override fun saveCallResult(item: LookupMoviesResponse, realmTransaction : Realm) {
                realmTransaction.copyToRealmOrUpdate(item.results)
            }
        }.asLiveData()
    }
}