package com.davidmag.movienatic.data.source.local.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.local.dto.GenreDb
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.Movie
import io.realm.RealmList
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object MovieLocalMapper : EntityDtoMapper<Movie, MovieDb>() {

    override val toDtoMapper: (Movie) -> MovieDb = {
        val moviedb = MovieDb()

        moviedb.id = it.id
        moviedb.backdropPath = it.backdropPath
        moviedb.genres = GenreLocalMapper.toDto(it.genres ?: listOf()).
            toCollection(RealmList())
        moviedb.overview = it.overview
        moviedb.posterPath = it.posterPath
        moviedb.releaseDate = it.releaseDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)
        moviedb.title = it.title

        moviedb
    }

    override val toEntityMapper: (MovieDb) -> Movie = {
        val movie = Movie()

        movie.id = it.id
        movie.backdropPath = it.backdropPath
        movie.genres = GenreLocalMapper.toEntity(it.genres ?: listOf()).
            toCollection(RealmList())
        movie.overview = it.overview
        movie.posterPath = it.posterPath
        movie.releaseDate = LocalDate.parse(it.releaseDate, DateTimeFormatter.ISO_LOCAL_DATE)
        movie.title = it.title

        movie
    }
}