package com.davidmag.movienatic.data.source.boundary.remote

import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Maybe

interface GenreRemoteDatasource {
    fun fetch(): Maybe<List<Genre>>
}