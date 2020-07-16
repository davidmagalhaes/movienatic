package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.ResultWrapper
import com.davidmag.movienatic.presentation.common.Result

class MovieListTabViewModel (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase
) : ViewModel() {

    val movies = MediatorLiveData<Result<List<Movie>>>()
    val imageConfigs = MediatorLiveData<Result<ImageConfigs>>()

    fun getMovies(genreId : Long) : LiveData<Result<List<Movie>>>  {
        return ResultWrapper.wrap(
            getMoviesUseCase.execute(genreId = genreId),
            movies
        )
    }

    fun getImageConfigs() : LiveData<Result<ImageConfigs>> {
        return ResultWrapper.wrapFirst(
            getImageConfigsUseCase.execute(),
            imageConfigs
        )
    }

    fun updateMovieList(genreId : Long) : LiveData<*> {
        return ResultWrapper.wrap(fetchMoviesUseCase.execute(genreId))
    }
}