package com.davidmag.movienatic.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.BaseViewModel
import com.davidmag.movienatic.presentation.common.GenericPresentationObject
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import com.davidmag.movienatic.presentation.mapper.MoviePresentationMapper

class MovieListTabViewModel (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase
) : BaseViewModel() {

    val movies = MediatorLiveData<List<MoviePresentation>>()
    val imageConfigs = MediatorLiveData<GenericPresentationObject<ImageConfigs>>()

    override fun init(args: Bundle?) {
        args?.let {
            PresentationWrapper.wrap(
                getMoviesUseCase.execute(genreId = it.getLong("genre_id"))
                    .map { movieList ->
                        MoviePresentationMapper.toDto(movieList)
                    },
                movies
            )

            PresentationWrapper.wrapGeneric(
                getImageConfigsUseCase.execute(),
                imageConfigs
            )
        } ?: throw Exception("Argument 'genre_id' not provided!")
    }

    fun fillMovieDetailsArgs(movie: MoviePresentation): Bundle {
        return Bundle().apply {
            putLong("id", movie.id)
        }
    }
}