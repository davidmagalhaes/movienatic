package com.davidmag.movienatic.data.source.remote.impl

import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
import com.davidmag.movienatic.data.source.remote.api.MovieApi
import com.davidmag.movienatic.data.source.remote.dto.LookupMoviesResponse
import com.davidmag.movienatic.data.source.remote.mapper.MovieRemoteMapper
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Maybe
import retrofit2.Call
import retrofit2.HttpException

class MovieRemoteDatasourceImpl(
    val movieApi: MovieApi
) : MovieRemoteDatasource {

    var currentCall : Call<*>? = null

    override fun query(query : String): Maybe<List<Movie>> {
        return findMovies(movieApi.search(BuildConfig.API_KEY, query))
    }

    override fun fetch() : Maybe<List<Movie>> {
        return findMovies(movieApi.lookup(BuildConfig.API_KEY, "28, 18, 14, 878"))
    }

    override fun find(id : String) : Maybe<Movie?> {
        return Maybe.fromCallable {
            currentCall?.cancel()
            val call = movieApi.findById(BuildConfig.API_KEY, id)

            currentCall = call

            val response = call.execute()

            if(response.isSuccessful){
                MovieRemoteMapper.toEntity(response.body()!!)
            }
            else {
                throw HttpException(response)
            }
        }
    }

    private fun findMovies(call : Call<LookupMoviesResponse>) : Maybe<List<Movie>> {
        return Maybe.fromCallable {
            currentCall?.cancel()
            currentCall = call

            val response = call.execute()

            if(response.isSuccessful){
                val body = response.body()
                if(body != null) {
                    MovieRemoteMapper.toEntity(body.results)
                } else {
                    listOf()
                }
            }
            else {
                throw HttpException(response)
            }
        }
    }

    override fun cancel(){
        currentCall?.cancel()
        currentCall = null
    }
}