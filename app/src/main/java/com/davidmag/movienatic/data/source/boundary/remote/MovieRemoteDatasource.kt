package com.davidmag.movienatic.data.source.boundary.remote

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Maybe

interface MovieRemoteDatasource {
    fun query(query : String) : Maybe<List<Movie>>
    fun fetch(genreId : Int? = null) : Maybe<List<Movie>>
    fun find(id : Int) : Maybe<Movie?>
    fun cancel()
}