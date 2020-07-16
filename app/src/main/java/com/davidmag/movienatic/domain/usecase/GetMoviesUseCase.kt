package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.repository.MovieRepository
import io.reactivex.Flowable

class GetMoviesUseCase(
    val movieRepository: MovieRepository
) {
    fun execute(id : Long? = null, genreId : Long? = null) : Flowable<List<Movie>> {
        return movieRepository.get(id, genreId)
    }
}