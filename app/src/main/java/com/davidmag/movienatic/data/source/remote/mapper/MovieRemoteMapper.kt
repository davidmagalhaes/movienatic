package com.davidmag.movienatic.data.source.remote.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.remote.dto.GenreObject
import com.davidmag.movienatic.data.source.remote.dto.LookupMoviesResponse
import com.davidmag.movienatic.data.source.remote.dto.MovieObject
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.Movie

object MovieRemoteMapper : EntityDtoMapper<Movie, MovieObject>() {
    override val toDtoMapper: (Movie) -> MovieObject
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val toEntityMapper: (MovieObject) -> Movie = {
        val entity = Movie()

        entity.id = it.id
        entity.title = it.title
        entity.posterPath = it.posterPath
        entity.releaseDate = it.releaseDate
        entity.overview = it.overview

        if(it.genres != null){
            entity.genres = GenreRemoteMapper.toEntity(it.genres ?: arrayListOf())
        }
        else if(it.genreIds != null){
            val genreList = arrayListOf<Genre>()

            it.genreIds?.forEach {
                genreList.add(Genre(id = it))
            }

            entity.genres = genreList
        }

        entity.backdropPath = it.backdropPath
        entity.lastUpdate = it.lastUpdate

        entity
    }
}