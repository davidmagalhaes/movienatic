package com.davidmag.movienatic.infrastructure.di

import com.davidmag.movienatic.domain.repository.GenreRepository
import com.davidmag.movienatic.domain.repository.ImageConfigsRepository
import com.davidmag.movienatic.domain.repository.MovieRepository
import com.davidmag.movienatic.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideFetchMovieDetailsByIdUseCase(
        movieRepository: MovieRepository
    ) : FetchMovieDetailsByIdUseCase {
        return FetchMovieDetailsByIdUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideFetchMoviesUseCase(
        genreRepository: GenreRepository,
        movieRepository: MovieRepository
    ) : FetchMoviesUseCase {
        return FetchMoviesUseCase(genreRepository, movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository
    ) : GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideSearchMoviesUseCase(
        movieRepository: MovieRepository
    ) : SearchMoviesUseCase {
        return SearchMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetImageConfigsUseCase(
        imageConfigsRepository: ImageConfigsRepository
    ) : GetImageConfigsUseCase {
        return GetImageConfigsUseCase(imageConfigsRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateImageConfigsUseCase(
        imageConfigsRepository: ImageConfigsRepository
    ) : UpdateImageConfigsUseCase {
        return UpdateImageConfigsUseCase(imageConfigsRepository)
    }

    @Singleton
    @Provides
    fun provideGetGenresUseCase(
        genreRepository: GenreRepository
    ) : GetGenresUseCase {
        return GetGenresUseCase(genreRepository)
    }
}