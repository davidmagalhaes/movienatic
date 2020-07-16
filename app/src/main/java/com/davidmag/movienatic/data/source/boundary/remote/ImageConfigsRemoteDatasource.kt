package com.davidmag.movienatic.data.source.boundary.remote

import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.Maybe

interface ImageConfigsRemoteDatasource {
    fun fetch() : Maybe<ImageConfigs>
}