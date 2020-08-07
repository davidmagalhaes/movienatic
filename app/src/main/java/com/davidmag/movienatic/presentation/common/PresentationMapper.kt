package com.davidmag.movienatic.presentation.common

interface PresentationMapper<Entity, DtoPresentation: PresentationObject> {
    fun toDto(entities: List<Entity>) : List<DtoPresentation>
}