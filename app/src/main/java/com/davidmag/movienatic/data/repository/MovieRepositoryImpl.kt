package com.davidmag.movienatic.data.repository

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    val localDatasource : MovieLocalDataSource,
    val remoteDatasource: MovieRemoteDatasource
) : MovieRepository {

    private val TAG = MovieRepositoryImpl::class.java.name

    override fun search(query: String): Maybe<List<Movie>> {
        return remoteDatasource.query(query).subscribeOn(Schedulers.io())
    }

    override fun find(id: String): Maybe<*> {
        return remoteDatasource.find(id).subscribeOn(Schedulers.io()).flatMap { movie ->
            localDatasource.patch(movie).subscribeOn(Schedulers.single())
        }
    }

    override fun fetch(): Maybe<*> {
        return remoteDatasource.fetch().subscribeOn(Schedulers.io()).
            flatMap {
                localDatasource.cache(it).subscribeOn(Schedulers.single())
            }
    }

    override fun get(id : String?, genreId : Int?): Flowable<List<Movie>> {
        return localDatasource.get(id, genreId).subscribeOn(Schedulers.single())
    }
}