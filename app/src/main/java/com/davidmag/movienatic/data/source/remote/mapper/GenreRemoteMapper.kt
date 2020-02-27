package com.davidmag.movienatic.data.source.remote.mapper

import com.davidmag.movienatic.data.source.common.EntityDtoMapper
import com.davidmag.movienatic.data.source.remote.dto.GenreObject
import com.davidmag.movienatic.domain.model.Genre

object GenreRemoteMapper : EntityDtoMapper<Genre, GenreObject>() {
    override val toDtoMapper: (Genre) -> GenreObject
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val toEntityMapper: (GenreObject) -> Genre = {
        val entity = Genre()

        entity.id = it.id
        entity.name = it.name

        entity
    }
}