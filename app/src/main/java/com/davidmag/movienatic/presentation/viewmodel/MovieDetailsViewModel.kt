package com.davidmag.movienatic.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetImageConfigsUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import com.davidmag.movienatic.presentation.common.BaseViewModel
import com.davidmag.movienatic.presentation.common.GenericPresentationObject
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import com.davidmag.movienatic.presentation.mapper.MoviePresentationMapper

class MovieDetailsViewModel (
    private val fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    val movie = MediatorLiveData<MoviePresentation>()
    val imageConfigs = MediatorLiveData<GenericPresentationObject<ImageConfigs>>()

    override fun init(args: Bundle?)  {
        args?.get("id")?.let { id ->
            PresentationWrapper.wrapGeneric(
                getImageConfigsUseCase.execute().firstElement(),
                imageConfigs
            )

            loadMovie(id as Long)

            PresentationWrapper.wrapFirst(
                getMoviesUseCase.execute(id = id)
                    .map {
                        MoviePresentationMapper.toDto(it)
                    },
                movie
            )
        } ?: throw Exception("Argument 'id' not provided!")
    }

    fun loadMovie(id: Long) {
        PresentationWrapper.attachOnce(
            fetchMovieDetailsByIdUseCase.execute(id),
            movie
        )
    }
}