package com.davidmag.movienatic.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.BaseViewModel
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import com.davidmag.movienatic.presentation.mapper.GenrePresentationMapper

class HomeViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val updateImageConfigsUseCase: UpdateImageConfigsUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val fetchGenresUseCase: FetchGenresUseCase
) : BaseViewModel() {

    val genres = MediatorLiveData<List<GenrePresentation>>()

    override fun init(args: Bundle?) {
        PresentationWrapper.wrap(
            getGenresUseCase.execute().map {
                GenrePresentationMapper.toDto(it)
            },
            genres
        )
    }

    fun updateGenres(){
        PresentationWrapper.attachOnce(
            fetchGenresUseCase.execute().onErrorComplete {
                val data = listOf(GenrePresentation(
                    viewType = PresentationObject.VIEWTYPE_ERROR
                ))

                genres.postValue(data)

                true
            },
            genres
        )
    }

    fun updateImageConfigs() {
        PresentationWrapper.attachOnce(
            updateImageConfigsUseCase.execute(),
            genres
        )
    }
}