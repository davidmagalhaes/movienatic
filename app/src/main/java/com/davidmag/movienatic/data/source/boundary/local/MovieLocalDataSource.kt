package com.davidmag.movienatic.data.source.boundary.local

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieLocalDataSource {
    fun get(id : Long? = null) : Flowable<List<Movie>>
    fun getByGenre(genreId : Long) : Flowable<List<Movie>>
    fun patch(movie : Movie) : Maybe<*>
    fun cache(movies : List<Movie>) : Maybe<*>
}