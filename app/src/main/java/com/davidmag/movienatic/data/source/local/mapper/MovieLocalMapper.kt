package com.davidmag.movienatic.data.source.local.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.domain.model.Movie

object MovieLocalMapper : EntityDtoMapper<Movie, MovieDb>() {

    override val toDtoMapper: (Movie) -> MovieDb = {
        val dto = MovieDb()

        dto.apply{
            id = it.id
            backdropPath = it.backdropPath
            overview = it.overview
            posterPath = it.posterPath
            releaseDate = it.releaseDate
            title = it.title
        }

        dto
    }

    override val toEntityMapper: (MovieDb) -> Movie = {
        val movie = Movie()

        with(movie){
            id = it.id
            backdropPath = it.backdropPath
            overview = it.overview
            posterPath = it.posterPath
            releaseDate = it.releaseDate
            title = it.title
        }

        movie
    }
}