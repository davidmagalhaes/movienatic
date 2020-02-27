package com.davidmag.movienatic.data.source.local.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.local.dto.ImageConfigsDb
import com.davidmag.movienatic.domain.model.ImageConfigs
import io.realm.RealmList
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object ImageConfigsLocalMapper : EntityDtoMapper<ImageConfigs, ImageConfigsDb>() {

    override val toDtoMapper: (ImageConfigs) -> ImageConfigsDb = {
        val dto = ImageConfigsDb()

        dto.id = it.id
        dto.backdropSizes = it.backdropSizes?.toCollection(RealmList())
        dto.baseUrl = it.baseUrl
        dto.lastUpdate = it.lastUpdate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        dto.logoSizes = it.logoSizes?.toCollection(RealmList())
        dto.posterSizes = it.posterSizes?.toCollection(RealmList())
        dto.secureBaseUrl = it.secureBaseUrl

        dto
    }

    override val toEntityMapper: (ImageConfigsDb) -> ImageConfigs = {
        val entity = ImageConfigs()

        entity.id = it.id
        entity.backdropSizes = it.backdropSizes?.toCollection(ArrayList())
        entity.baseUrl = it.baseUrl
        entity.lastUpdate = OffsetDateTime.parse(it.lastUpdate, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        entity.logoSizes = it.logoSizes?.toCollection(ArrayList())
        entity.posterSizes = it.posterSizes?.toCollection(ArrayList())
        entity.secureBaseUrl = it.secureBaseUrl

        entity
    }

}