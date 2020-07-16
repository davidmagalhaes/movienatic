package com.davidmag.movienatic.data.source.boundary.local

import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GenreLocalDatasource {
    fun cache(genres: List<Genre>): Maybe<Any>
    fun get(): Flowable<List<Genre>>
}