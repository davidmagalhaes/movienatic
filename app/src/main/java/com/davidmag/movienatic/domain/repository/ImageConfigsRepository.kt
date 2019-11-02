package com.davidmag.movienatic.domain.repository

import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.Flowable
import io.reactivex.Maybe

interface ImageConfigsRepository {
    fun fetch() : Maybe<*>
    fun get() : Flowable<List<ImageConfigs>>
}