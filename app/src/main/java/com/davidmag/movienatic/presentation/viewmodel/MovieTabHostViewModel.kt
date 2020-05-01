package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.ResultWrapper
import com.davidmag.movienatic.presentation.common.Result

class MovieTabHostViewModel(
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val updateImageConfigsUseCase: UpdateImageConfigsUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    val imageConfigs = MediatorLiveData<Result<ImageConfigs>>()
    val genres = MediatorLiveData<Result<List<Genre>>>()

    fun updateImageConfigs() : LiveData<Result<Any>> {
        return ResultWrapper.wrap(updateImageConfigsUseCase.execute())
    }

    fun searchMovies(query : String) : LiveData<Result<List<Movie>>> {
        return ResultWrapper.wrap(searchMoviesUseCase.execute(query))
    }

    fun getGenres() : LiveData<Result<List<Genre>>> {
        return ResultWrapper.wrap(
            getGenresUseCase.execute(),
            genres
        )
    }

    fun getImageConfigs() : LiveData<Result<ImageConfigs>> {
        return ResultWrapper.wrapFirst(
            getImageConfigsUseCase.execute(),
            imageConfigs
        )
    }
}