package com.davidmag.movienatic.data.source.local.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.local.dto.MovieFullJoin
import com.davidmag.movienatic.domain.model.Movie

object MovieFullJoinLocalMapper : EntityDtoMapper<Movie, MovieFullJoin>()  {
    override val toEntityMapper: (MovieFullJoin) -> Movie = {
        val entity = MovieLocalMapper.toEntity(it.movie!!)

        entity.genres = GenreLocalMapper.toEntity(it.genres.orEmpty())

        entity
    }

    override val toDtoMapper: (Movie) -> MovieFullJoin = {
        val movieDto = MovieLocalMapper.toDto(it)
        val genres = GenreLocalMapper.toDto(it.genres.orEmpty())
        val movieFullJoin = MovieFullJoin()

        movieFullJoin.movie = movieDto
        movieFullJoin.genres = genres

        movieFullJoin
    }
}