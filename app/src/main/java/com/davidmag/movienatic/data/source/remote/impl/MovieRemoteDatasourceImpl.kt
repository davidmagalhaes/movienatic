package com.davidmag.movienatic.data.source.remote.impl

import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
import com.davidmag.movienatic.data.source.remote.api.MovieApi
import com.davidmag.movienatic.data.source.remote.dto.LookupMoviesResponse
import com.davidmag.movienatic.data.source.remote.mapper.MovieRemoteMapper
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.BackpressureStrategy
import io.reactivex.Maybe
import retrofit2.Call
import retrofit2.HttpException

class MovieRemoteDatasourceImpl(
    val movieApi: MovieApi
) : MovieRemoteDatasource {

    override fun query(query : String): Maybe<List<Movie>> {
        return movieApi.search(BuildConfig.API_KEY, query)
            .map {
                MovieRemoteMapper.toEntity(it.results)
            }
    }

    override fun fetch(genreId : Long?) : Maybe<List<Movie>> {
        return movieApi.lookup(BuildConfig.API_KEY,
                if(genreId != null) "$genreId" else "28, 18, 14, 878")
            .map {
                MovieRemoteMapper.toEntity(it.results)
            }
    }

    override fun find(id : Long) : Maybe<Movie?> {
        return movieApi.findById("$id", BuildConfig.API_KEY)
            .map {
                MovieRemoteMapper.toEntity(it)
            }
    }
}