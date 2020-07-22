package com.davidmag.movienatic.data.repository

import com.davidmag.movienatic.data.source.boundary.local.GenreLocalDatasource
import com.davidmag.movienatic.data.source.boundary.remote.GenreRemoteDatasource
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.repository.GenreRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class GenreRepositoryImpl(
    val genreRemoteDatasource: GenreRemoteDatasource,
    val genreLocalDatasource: GenreLocalDatasource
) : GenreRepository {
    override fun fetch(): Maybe<Any> {
        return genreRemoteDatasource.fetch()
            .subscribeOn(Schedulers.io())
            .flatMap {
                genreLocalDatasource.cache(it)
                    .subscribeOn(Schedulers.single())
            }
    }

    override fun get(): Flowable<List<Genre>> {
        return genreLocalDatasource.get()
            .subscribeOn(Schedulers.single())
    }
}