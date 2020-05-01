package com.davidmag.movienatic.domain.repository

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieRepository {
    fun search(query : String) : Maybe<List<Movie>>
    fun fetch(genreId : Int? = null) : Maybe<Any>
    fun find(id : Int) : Maybe<Any>
    fun get(id : Int? = null, genreId : Int? = null) : Flowable<List<Movie>>
}