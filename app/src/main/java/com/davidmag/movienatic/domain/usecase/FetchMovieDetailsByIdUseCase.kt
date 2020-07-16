package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Maybe

class FetchMovieDetailsByIdUseCase (
    val movieRepository: MovieRepository
) {
    fun execute(id : Long) : Maybe<*> {
        return movieRepository.find(id)
    }
}