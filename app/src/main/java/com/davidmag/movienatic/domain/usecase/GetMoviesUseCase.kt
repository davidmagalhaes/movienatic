package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Flowable

class GetMoviesUseCase(
    val movieRepository: MovieRepository
) {
    fun execute(id : String? = null, genreId : Int? = null) : Flowable<List<Movie>> {
        return movieRepository.get(id, genreId)
    }
}