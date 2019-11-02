package com.davidmag.movienatic.domain.usecase

import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.repository.ImageConfigsRepository
import io.reactivex.Flowable

class GetImageConfigsUseCase (
    val imageConfigsRepository: ImageConfigsRepository
) {
    fun execute() : Flowable<List<ImageConfigs>> {
        return imageConfigsRepository.get()
    }
}