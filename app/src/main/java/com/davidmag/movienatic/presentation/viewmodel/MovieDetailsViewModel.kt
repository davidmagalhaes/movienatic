package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    val fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {



}