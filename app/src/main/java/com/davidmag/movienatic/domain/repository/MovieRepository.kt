package com.davidmag.movienatic.domain.repository

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieRepository {
    fun search(query : String) : Maybe<List<Movie>>
    fun fetch(genreId : Long? = null) : Maybe<Any>
    fun find(id : Long) : Maybe<Any>
    fun get(id : Long? = null, genreId : Long? = null) : Flowable<List<Movie>>
}