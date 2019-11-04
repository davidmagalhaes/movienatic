package com.davidmag.movienatic.domain.repository

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieRepository {
    fun search(query : String) : Maybe<List<Movie>>
    fun fetch(genreId : Int? = null) : Maybe<*>
    fun find(id : String) : Maybe<*>
    fun get(id : String? = null, genreId : Int? = null) : Flowable<List<Movie>>
}