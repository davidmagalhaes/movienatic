package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Flowable

class GetGenresUseCase {
    fun execute() : Flowable<List<Genre>> {
        return Flowable.just(arrayListOf(
            Genre(id = 28, name = "Action"),
            Genre(id = 18, name = "Drama"),
            Genre(id = 14, name = "Fantasy"),
            Genre(id = 878, name = "Science Fiction")
        ))
    }
}