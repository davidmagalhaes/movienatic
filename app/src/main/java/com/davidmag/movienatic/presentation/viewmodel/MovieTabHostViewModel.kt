package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.*
import io.reactivex.Maybe
import javax.inject.Inject

class MovieTabHostViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getImageConfigsUseCase: GetImageConfigsUseCase

    @Inject
    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Inject
    lateinit var updateImageConfigsUseCase: UpdateImageConfigsUseCase

    @Inject
    lateinit var getGenresUseCase: GetGenresUseCase

    fun updateImageConfigs() : LiveData<*>{
        return LiveDataReactiveStreams.fromPublisher(updateImageConfigsUseCase.execute().toFlowable())
    }

    fun searchMovies(query : String) : LiveData<List<Movie>>{
        return LiveDataReactiveStreams.fromPublisher(searchMoviesUseCase.execute(query).toFlowable())
    }

    fun getGenres() : LiveData<List<Genre>> {
        return LiveDataReactiveStreams.fromPublisher(getGenresUseCase.execute().toFlowable())
    }

    fun getImageConfigs() : LiveData<List<ImageConfigs>> {
        return LiveDataReactiveStreams.fromPublisher(getImageConfigsUseCase.execute())
    }
}