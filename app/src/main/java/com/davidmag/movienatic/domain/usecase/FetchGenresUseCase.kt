package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.repository.GenreRepository
import io.reactivex.Maybe

class FetchGenresUseCase(
    val genreRepository: GenreRepository
) {
    fun execute() : Maybe<Any> {
        return genreRepository.fetch()
    }
}