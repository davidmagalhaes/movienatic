package com.davidmag.movienatic.data.source.boundary.remote

import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Maybe

interface MovieRemoteDatasource {
    fun query(query : String) : Maybe<List<Movie>>
    fun fetch() : Maybe<List<Movie>>
    fun find(id : String) : Maybe<Movie?>
    fun cancel()
}