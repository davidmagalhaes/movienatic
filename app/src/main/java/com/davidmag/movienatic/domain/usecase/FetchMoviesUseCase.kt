package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Maybe

class FetchMoviesUseCase (
    val movieRepository: MovieRepository
) {
    fun execute() : Maybe<*> {
        return movieRepository.fetch()
    }
}