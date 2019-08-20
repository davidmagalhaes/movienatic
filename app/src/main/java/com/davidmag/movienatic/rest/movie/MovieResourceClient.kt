package com.davidmag.movienatic.rest.movie

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.ApiUtils
import kotlinx.coroutines.*

object MovieResourceClient {
    val movies = MutableLiveData<List<Movie>?>()

    private val movieResource by lazy {
        App.retrofit().create(MovieResource::class.java)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : CompletableDeferred<List<Movie>?> {
        val call = movieResource.getUpcomingMovies(BuildConfig.API_KEY, page, language, region)
        return ApiUtils.doRequest(call){response, exception ->
            exception?.let {
                movies.postValue(null)
            }
            response?.let {
                val results = it.body()!!.results
                movies.postValue(results)
                results
            }
        }
    }

    fun searchMovies(query : String, includeAdult : Boolean? = null, year : Int? = null, primaryReleaseYear : Int? = null,
                     page : Int? = null, language : String? = null, region : String? = null) : CompletableDeferred<List<Movie>?> {

        val call = movieResource.searchMovies(BuildConfig.API_KEY, query, includeAdult, year, primaryReleaseYear, page, language, region)
        return ApiUtils.doRequest(call){response, exception ->
            exception?.let {
                movies.postValue(null)
            }
            response?.let {
                val results = it.body()!!.results
                movies.postValue(results)
                results
            }
        }
    }


}


/*private fun lookupMovies(call : Call<LookupMoviesResponse>) : CompletableDeferred<List<Movie>>{
    val completableDeferred = CompletableDeferred<List<Movie>>()

    GlobalScope.launch {
        try{
            val deferred = async {
                try{
                    val response = call.execute()

                    if(response.isSuccessful){
                        val results = response.body()!!.results
                        completableDeferred.complete(results)
                        movies.postValue(results)
                    }
                    else {
                        throw HttpException(response)
                    }
                }
                catch(e : Exception){
                    e.printStackTrace()
                    completableDeferred.completeExceptionally(e)
                    movies.postValue(null)
                }
            }

            withTimeout(Constants.NETWORK_TIMEOUT){
                deferred.await()
            }
        }
        catch (e : TimeoutCancellationException){
            call.cancel()
        }
    }

    return completableDeferred
}*/