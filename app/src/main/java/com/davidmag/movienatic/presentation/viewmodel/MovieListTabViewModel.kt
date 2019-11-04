package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.FetchMoviesUseCase
import com.davidmag.movienatic.domain.usecase.GetImageConfigsUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import javax.inject.Inject

class MovieListTabViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase,
    val fetchMoviesUseCase: FetchMoviesUseCase,
    val getImageConfigsUseCase: GetImageConfigsUseCase
) : ViewModel() {

    fun getMovies(genreId : Int) : LiveData<List<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(getMoviesUseCase.execute(genreId = genreId))
    }

    fun updateMovieList() : LiveData<*> {
        return LiveDataReactiveStreams.fromPublisher(fetchMoviesUseCase.execute().toFlowable())
    }

    fun getImageConfigs() : LiveData<List<ImageConfigs>> {
        return LiveDataReactiveStreams.fromPublisher(getImageConfigsUseCase.execute())
    }
}