package com.davidmag.movienatic.presentation.mapper

import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.presentation.common.PresentationMapper
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.MoviePresentation

object MoviePresentationMapper : PresentationMapper<Movie, MoviePresentation>{
    override fun toDto(entities: List<Movie>): List<MoviePresentation> {
        val result = ArrayList<MoviePresentation>(entities.size)

        entities.forEach {
            result.add(
                MoviePresentation(
                    viewType = PresentationObject.VIEWTYPE_CONTENT,
                    title = it.title,
                    posterPath = it.posterPath
                )
            )
        }

        return result
    }
}