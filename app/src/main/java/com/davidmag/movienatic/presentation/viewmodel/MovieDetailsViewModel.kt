package com.davidmag.movienatic.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetImageConfigsUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import com.davidmag.movienatic.presentation.common.BaseViewModel
import com.davidmag.movienatic.presentation.common.ContentPresentationObjectImpl
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import com.davidmag.movienatic.presentation.mapper.MoviePresentationMapper

class MovieDetailsViewModel (
    private val fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    val movie = MediatorLiveData<MoviePresentation>()
    val imageConfigs = MediatorLiveData<ContentPresentationObjectImpl<ImageConfigs>>()

    private var movieId: Long = -1

    override fun init(args: Bundle?)  {
        movieId = (args?.get("id") as Long?) ?: throw Exception("Argument 'id' not provided!")

        PresentationWrapper.wrapGeneric(
            getImageConfigsUseCase.execute().firstElement(),
            imageConfigs
        )

        PresentationWrapper.wrapFirst(
            getMoviesUseCase.execute(id = movieId)
                .map {
                    MoviePresentationMapper.toDto(it)
                },
            movie
        )

        loadMovie()
    }

    fun loadMovie() {
        PresentationWrapper.attachOnce(
            fetchMovieDetailsByIdUseCase.execute(movieId),
            movie
        )
    }
}