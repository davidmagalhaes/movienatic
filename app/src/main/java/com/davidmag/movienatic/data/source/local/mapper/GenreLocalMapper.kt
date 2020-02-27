package com.davidmag.movienatic.data.source.local.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.local.dto.GenreDb
import com.davidmag.movienatic.domain.model.Genre

object GenreLocalMapper : EntityDtoMapper<Genre, GenreDb>() {
    override val toDtoMapper: (Genre) -> GenreDb = {
        val dto = GenreDb()

        dto.id = it.id
        dto.name = it.name

        dto
    }

    override val toEntityMapper: (GenreDb) -> Genre = {
        val entity = Genre()

        entity.id = it.id
        entity.name = it.name

        entity
    }
}