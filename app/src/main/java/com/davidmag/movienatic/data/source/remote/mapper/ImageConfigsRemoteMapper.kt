package com.davidmag.movienatic.data.source.remote.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.remote.dto.ImageConfigsObject
import com.davidmag.movienatic.domain.model.ImageConfigs

object ImageConfigsRemoteMapper : EntityDtoMapper<ImageConfigs, ImageConfigsObject>() {
    override val toEntityMapper: (ImageConfigsObject) -> ImageConfigs
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val toDtoMapper: (ImageConfigs) -> ImageConfigsObject = {
        val dto = ImageConfigsObject()

        dto.id = it.id
        dto.backdropSizes = it.backdropSizes
        dto.baseUrl = it.baseUrl
        dto.lastUpdate = it.lastUpdate
        dto.logoSizes = it.logoSizes
        dto.posterSizes = it.posterSizes
        dto.secureBaseUrl = it.secureBaseUrl

        dto
    }


}