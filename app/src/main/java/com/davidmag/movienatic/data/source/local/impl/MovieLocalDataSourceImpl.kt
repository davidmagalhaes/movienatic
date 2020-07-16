package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.local.dao.MovieDao
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.data.source.local.mapper.GenreLocalMapper
import com.davidmag.movienatic.data.source.local.mapper.MovieFullJoinLocalMapper
import com.davidmag.movienatic.data.source.local.mapper.MovieLocalMapper
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override fun cache(movies: List<Movie>): Maybe<*> {
        return Maybe.fromCallable {
            movieDao.cacheWithGenres(
                MovieFullJoinLocalMapper.toDto(movies)
            )
        }.map {
            movieDao.upsertWithGenres(
                MovieFullJoinLocalMapper.toDto(movies)
            )
        }
    }

    override fun patch(movie: Movie): Maybe<*> {
        return Maybe.fromCallable {
            movieDao.upsertWithGenres(listOf(MovieFullJoinLocalMapper.toDto(movie)))
        }
    }

    override fun get(id : Long?) : Flowable<List<Movie>> {
        return when {
            id != null -> movieDao.find(id)
            else -> movieDao.get()
        }.map {
            MovieFullJoinLocalMapper.toEntity(it)
        }
    }

    override fun getByGenre(genreId : Long) : Flowable<List<Movie>> {
        return movieDao.get(genreId).map {
            MovieFullJoinLocalMapper.toEntity(it)
        }
    }
}