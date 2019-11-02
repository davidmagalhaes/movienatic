package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Maybe

class SearchMoviesUseCase(
    val movieRepository: MovieRepository
) {
    fun execute(query : String) : Maybe<List<Movie>> {
        return movieRepository.search(query)
    }
}