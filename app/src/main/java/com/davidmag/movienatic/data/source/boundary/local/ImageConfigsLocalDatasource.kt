package com.davidmag.movienatic.data.source.boundary.local

import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.Flowable
import io.reactivex.Maybe

interface ImageConfigsLocalDatasource {
    fun cache(configs : ImageConfigs) : Maybe<*>
    fun get() : Flowable<List<ImageConfigs>>
}