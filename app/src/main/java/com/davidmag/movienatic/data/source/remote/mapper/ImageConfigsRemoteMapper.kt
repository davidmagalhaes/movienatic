package com.davidmag.movienatic.data.source.remote.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.remote.dto.ImageConfigsObject
import com.davidmag.movienatic.domain.model.ImageConfigs

object ImageConfigsRemoteMapper : EntityDtoMapper<ImageConfigs, ImageConfigsObject>() {
    override val toEntityMapper: (ImageConfigsObject) -> ImageConfigs = {
        val entity = ImageConfigs()

        entity.id = 1
        entity.backdropSizes = it.backdropSizes
        entity.baseUrl = it.baseUrl
        entity.lastUpdate = it.lastUpdate
        entity.logoSizes = it.logoSizes
        entity.posterSizes = it.posterSizes
        entity.secureBaseUrl = it.secureBaseUrl

        entity
    }


    override val toDtoMapper: (ImageConfigs) -> ImageConfigsObject
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}