package com.davidmag.movienatic.data.source.common

abstract class EntityDtoMapper<EntityType, DtoType> {

    abstract val toDtoMapper : (EntityType) -> DtoType
    abstract val toEntityMapper : (DtoType) -> EntityType

    fun toDto(entity : EntityType) : DtoType{
        return toDtoMapper(entity)
    }

    fun toEntity(dto : DtoType) : EntityType {
        return toEntityMapper(dto)
    }

    fun toDto(entities : List<EntityType>) : List<DtoType> {
        return entities.map { toDtoMapper(it) }
    }

    fun toEntity(dtos : List<DtoType>) : List<EntityType>{
        return dtos.map { toEntityMapper(it) }
    }
}