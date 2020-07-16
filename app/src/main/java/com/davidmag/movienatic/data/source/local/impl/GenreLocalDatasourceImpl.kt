package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.GenreLocalDatasource
import com.davidmag.movienatic.data.source.local.dao.GenreDao
import com.davidmag.movienatic.data.source.local.mapper.GenreLocalMapper
import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Flowable
import io.reactivex.Maybe

class GenreLocalDatasourceImpl(
    val genreDao: GenreDao
): GenreLocalDatasource {
    override fun cache(genres: List<Genre>): Maybe<Any> {
        return Maybe.fromCallable {
            genreDao.cache(
                *GenreLocalMapper.toDto(genres).toTypedArray()
            )
        }
    }

    override fun get(): Flowable<List<Genre>> {
        return genreDao.get().map {
            GenreLocalMapper.toEntity(it)
        }
    }
}