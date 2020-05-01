package com.davidmag.movienatic.data.repository

import com.davidmag.movienatic.data.source.boundary.local.ImageConfigsLocalDatasource
import com.davidmag.movienatic.data.source.boundary.remote.ImageConfigsRemoteDatasource
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.repository.ImageConfigsRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class ImageConfigsRepositoryImpl(
    val imageConfigsLocalDatasource: ImageConfigsLocalDatasource,
    val imageConfigsRemoteDatasource: ImageConfigsRemoteDatasource
) : ImageConfigsRepository {

    override fun fetch(): Maybe<Any> {
        return imageConfigsRemoteDatasource.fetch().subscribeOn(Schedulers.io()).flatMap {
            imageConfigsLocalDatasource.cache(it)
        }
    }

    override fun get(): Flowable<List<ImageConfigs>> {
        return imageConfigsLocalDatasource.get()
    }
}