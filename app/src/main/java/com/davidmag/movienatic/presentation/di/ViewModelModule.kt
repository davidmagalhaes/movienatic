package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListViewModel
import com.davidmag.movienatic.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMovieTabHostViewModel(
        fetchMoviesUseCase: FetchMoviesUseCase,
        updateImageConfigsUseCase: UpdateImageConfigsUseCase,
        getGenresUseCase: GetGenresUseCase,
        fetchGenresUseCase: FetchGenresUseCase
    ) : HomeViewModel {
        return HomeViewModel(
            fetchMoviesUseCase,
            updateImageConfigsUseCase,
            getGenresUseCase,
            fetchGenresUseCase
        )
    }

    @Provides
    fun provideMovieListTabViewModel(
        getMoviesUseCase: GetMoviesUseCase,
        getImageConfigsUseCase: GetImageConfigsUseCase,
        fetchMoviesUseCase: FetchMoviesUseCase
    ) : MovieListViewModel {
        return MovieListViewModel(
            getMoviesUseCase,
            getImageConfigsUseCase,
            fetchMoviesUseCase
        )
    }

    @Provides
    fun provideMovieDetailsViewModel(
        fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
        getImageConfigsUseCase: GetImageConfigsUseCase,
        getMoviesUseCase: GetMoviesUseCase
    ) : MovieDetailsViewModel {
        return MovieDetailsViewModel(
            fetchMovieDetailsByIdUseCase,
            getImageConfigsUseCase,
            getMoviesUseCase
        )
    }
}