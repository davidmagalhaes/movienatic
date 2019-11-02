package com.davidmag.movienatic.data.source.boundary.local

import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieLocalDataSource {
    fun get(id : String? = null, genreId : Int? = null) : Flowable<List<Movie>>
    fun patch(movie : Movie) : Maybe<*>
    fun cache(movies : List<Movie>) : Maybe<*>
}