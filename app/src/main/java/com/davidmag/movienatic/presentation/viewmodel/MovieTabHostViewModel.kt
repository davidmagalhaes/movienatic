package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import com.davidmag.movienatic.presentation.mapper.GenrePresentationMapper
import io.reactivex.Maybe

class MovieTabHostViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val updateImageConfigsUseCase: UpdateImageConfigsUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    val genres = MediatorLiveData<List<GenrePresentation>>()
    val status = MediatorLiveData<PresentationObject>()

    fun updateImageConfigs() : LiveData<PresentationObject> {
        return PresentationWrapper.wrapSubmit(
            updateImageConfigsUseCase.execute()
        )
    }

    fun getGenres() : LiveData<List<GenrePresentation>> {
        return PresentationWrapper.wrap(
            getGenresUseCase.execute().map {
                GenrePresentationMapper.toDto(it)
            },
            genres
        )
    }

    fun updateMovieList(vararg genreId : Long) : LiveData<PresentationObject> {
        val result = Maybe.just(Any())

        genreId.forEach { eachId ->
            result.flatMap {
                fetchMoviesUseCase.execute(eachId)
            }
        }

        return PresentationWrapper.wrapSubmit(result)
    }
}