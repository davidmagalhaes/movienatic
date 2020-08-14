package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.repository.GenreRepository
import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Maybe

class FetchMoviesUseCase (
    val genreRepository: GenreRepository,
    val movieRepository: MovieRepository
) {
    fun execute(genreId : Long? = null) : Maybe<Any> {
        return movieRepository.fetch(genreId)
    }
}