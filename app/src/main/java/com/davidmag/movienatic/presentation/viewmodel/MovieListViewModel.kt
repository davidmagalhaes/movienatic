package com.davidmag.movienatic.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.common.BaseViewModel
import com.davidmag.movienatic.presentation.common.ContentPresentationObjectImpl
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.PresentationWrapper
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import com.davidmag.movienatic.presentation.mapper.MoviePresentationMapper

class MovieListViewModel (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : BaseViewModel() {

    val movies = MediatorLiveData<List<MoviePresentation>>()
    val imageConfigs = MediatorLiveData<ContentPresentationObjectImpl<ImageConfigs>>()

    private var genreId: Long = -1

    override fun init(args: Bundle?) {
        args?.let {
            genreId = it.getLong("genre_id")

            movies.postValue(
                listOf(MoviePresentation(
                    viewType = PresentationObject.VIEWTYPE_WAITING
                ))
            )

            PresentationWrapper.wrap(
                getMoviesUseCase.execute(genreId = genreId)
                    .map { movieList ->
                        MoviePresentationMapper.toDto(movieList)
                    },
                movies
            )

            PresentationWrapper.wrapGeneric(
                getImageConfigsUseCase.execute(),
                imageConfigs
            )

            updateMovies()
        } ?: throw Exception("Argument 'genre_id' not provided!")
    }

    fun fillMovieDetailsArgs(movie: MoviePresentation): Bundle {
        return Bundle().apply {
            putLong("id", movie.id)
        }
    }

    fun updateMovies(){
        PresentationWrapper.attachOnce(
            fetchMoviesUseCase.execute(genreId),
            movies
        )
    }
}