package com.davidmag.movienatic.domain.repository

import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GenreRepository {
    fun fetch(): Maybe<Any>
    fun get(): Flowable<List<Genre>>
}