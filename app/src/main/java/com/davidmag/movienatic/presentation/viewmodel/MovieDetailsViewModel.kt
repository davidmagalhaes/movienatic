package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase

    @Inject
    lateinit var getMoviesUseCase: GetMoviesUseCase


}