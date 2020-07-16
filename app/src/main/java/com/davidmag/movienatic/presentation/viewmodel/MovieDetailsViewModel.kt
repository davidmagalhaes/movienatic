package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetImageConfigsUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import com.davidmag.movienatic.presentation.common.ResultWrapper
import com.davidmag.movienatic.presentation.common.Result

class MovieDetailsViewModel (
    private val fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val movie = MediatorLiveData<Result<Movie>>()
    val imageConfigs = MediatorLiveData<Result<ImageConfigs>>()

    fun getMovies(id: Long) : LiveData<Result<Movie>> {
        return ResultWrapper.wrapFirst(
            getMoviesUseCase.execute(id = id),
            movie
        )
    }

    fun getImageConfigs() : LiveData<Result<ImageConfigs>> {
        return ResultWrapper.wrapFirst(
            getImageConfigsUseCase.execute(),
            imageConfigs
        )
    }

    fun init(id : Long) : LiveData<*> {
        return ResultWrapper.wrap(fetchMovieDetailsByIdUseCase.execute(id))
    }
}