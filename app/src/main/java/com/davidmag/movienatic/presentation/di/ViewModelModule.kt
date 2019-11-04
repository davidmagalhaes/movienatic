package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMovieTabHostViewModel(
        getImageConfigsUseCase: GetImageConfigsUseCase,
        searchMoviesUseCase: SearchMoviesUseCase,
        updateImageConfigsUseCase: UpdateImageConfigsUseCase,
        getGenresUseCase: GetGenresUseCase
    ) : MovieTabHostViewModel {
        return MovieTabHostViewModel(
            getImageConfigsUseCase,
            searchMoviesUseCase,
            updateImageConfigsUseCase,
            getGenresUseCase
        )
    }

    @Provides
    fun provideMovieListTabViewModel(
        getMoviesUseCase: GetMoviesUseCase,
        fetchMoviesUseCase: FetchMoviesUseCase,
        getImageConfigsUseCase: GetImageConfigsUseCase
    ) : MovieListTabViewModel {
        return MovieListTabViewModel(
            getMoviesUseCase,
            fetchMoviesUseCase,
            getImageConfigsUseCase
        )
    }

    @Provides
    fun provideMovieDetailsViewModel(
        fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
        getMoviesUseCase: GetMoviesUseCase
    ) : MovieDetailsViewModel {
        return MovieDetailsViewModel(
            fetchMovieDetailsByIdUseCase,
            getMoviesUseCase
        )
    }
}