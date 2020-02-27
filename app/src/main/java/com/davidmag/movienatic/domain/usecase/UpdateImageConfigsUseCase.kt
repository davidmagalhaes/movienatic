package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.repository.ImageConfigsRepository
import io.reactivex.Maybe

class UpdateImageConfigsUseCase(
    val imageConfigsRepository: ImageConfigsRepository
) {
    fun execute() : Maybe<*> {
        return imageConfigsRepository.fetch()
    }
}