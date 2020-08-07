package com.davidmag.movienatic.presentation.mapper

import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.presentation.common.PresentationMapper
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.GenrePresentation

object GenrePresentationMapper : PresentationMapper<Genre, GenrePresentation> {
    override fun toDto(entities: List<Genre>): List<GenrePresentation> {
        val result = ArrayList<GenrePresentation>(entities.size)

        entities.forEach {
            result.add(
                GenrePresentation(
                    viewType = PresentationObject.DEFAULT_VIEWTYPE_CONTENT,
                    id = it.id,
                    description = it.name
                )
            )
        }

        return result
    }
}