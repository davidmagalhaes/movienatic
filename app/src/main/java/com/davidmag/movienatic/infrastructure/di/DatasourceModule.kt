package com.davidmag.movienatic.infrastructure.di

import com.davidmag.movienatic.data.source.boundary.local.ImageConfigsLocalDatasource
import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.boundary.remote.ImageConfigsRemoteDatasource
import com.davidmag.movienatic.data.source.boundary.remote.MovieRemoteDatasource
import com.davidmag.movienatic.data.source.local.impl.ImageConfigsLocalDatasourceImpl
import com.davidmag.movienatic.data.source.local.impl.MovieLocalDataSourceImpl
import com.davidmag.movienatic.data.source.remote.impl.MovieRemoteDatasourceImpl
import com.davidmag.movienatic.data.source.remote.api.ConfigurationsApi
import com.davidmag.movienatic.data.source.remote.api.MovieApi
import com.davidmag.movienatic.data.source.remote.impl.ImageConfigsRemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatasourceModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDatasource(
        movieApi: MovieApi
    ) : MovieRemoteDatasource {
        return MovieRemoteDatasourceImpl(movieApi)
    }

    @Singleton
    @Provides
    fun provideImageConfigsRemoteDatasource(
        configurationsApi: ConfigurationsApi
    ) : ImageConfigsRemoteDatasource {
        return ImageConfigsRemoteDatasourceImpl(configurationsApi)
    }

    @Singleton
    @Provides
    fun provideMovieLocalDatasource() : MovieLocalDataSource {
        return MovieLocalDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideImageConfigsLocalDatasource() : ImageConfigsLocalDatasource {
        return ImageConfigsLocalDatasourceImpl()
    }
}