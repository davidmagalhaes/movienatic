package com.davidmag.movienatic.infrastructure.di

import com.davidmag.movienatic.data.repository.GenreRepositoryImpl
import com.davidmag.movienatic.data.repository.ImageConfigsRepositoryImpl
import com.davidmag.movienatic.data.repository.MovieRepositoryImpl
import com.davidmag.movienatic.data.source.boundary.local.GenreLocalDatasource
import com.davidmag.movienatic.data.source.boundary.local.ImageConfigsLocalDatasource
import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.boundary.remote.GenreRemoteDatasource
import com.davidmag.movienatic.data.source.boundary.remote.ImageConfigsRemoteDatasource
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
import com.davidmag.movienatic.domain.repository.GenreRepository
import com.davidmag.movienatic.domain.repository.ImageConfigsRepository
import com.davidmag.movienatic.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        localDataSource: MovieLocalDataSource,
        remoteDatasource: MovieRemoteDatasource
    ) : MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDatasource)
    }

    @Singleton
    @Provides
    fun provideImageConfigsRepository(
        imageConfigsLocalDatasource: ImageConfigsLocalDatasource,
        imageConfigsRemoteDatasource: ImageConfigsRemoteDatasource
    ): ImageConfigsRepository {
        return ImageConfigsRepositoryImpl(
            imageConfigsLocalDatasource,
            imageConfigsRemoteDatasource
        )
    }

    @Singleton
    @Provides
    fun provideGenreRepository(
        genreRemoteDatasource: GenreRemoteDatasource,
        genreLocalDatasource: GenreLocalDatasource
    ): GenreRepository =
        GenreRepositoryImpl(
            genreRemoteDatasource,
            genreLocalDatasource
        )
}