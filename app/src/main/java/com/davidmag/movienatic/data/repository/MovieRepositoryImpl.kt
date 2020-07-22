package com.davidmag.movienatic.data.repository

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
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
        return remoteDatasource.query(query)
            .subscribeOn(Schedulers.io())
    }

    override fun find(id: Long): Maybe<Any> {
        return remoteDatasource.find(id)
            .subscribeOn(Schedulers.io())
            .flatMap { movie ->
                localDatasource.patch(movie)
                    .observeOn(Schedulers.single())
            }
    }

    override fun fetch(genreId : Long?): Maybe<Any> {
        return remoteDatasource.fetch(genreId)
            .subscribeOn(Schedulers.io())
            .flatMap {
                localDatasource.cache(it).subscribeOn(Schedulers.single())
                    .observeOn(Schedulers.single())
            }
    }

    override fun get(id : Long?, genreId : Long?): Flowable<List<Movie>> {
        return genreId?.let {
            localDatasource.getByGenre(it)
                .observeOn(Schedulers.single())
        } ?: localDatasource.get(id)
    }
}