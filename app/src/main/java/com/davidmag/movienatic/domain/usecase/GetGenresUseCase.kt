package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.repository.GenreRepository
import io.reactivex.Flowable

class GetGenresUseCase(
    val genreRepository: GenreRepository
) {
    fun execute() : Flowable<List<Genre>> {
        return genreRepository.fetch().toFlowable().flatMap {
            genreRepository.get()
        }
    }
}