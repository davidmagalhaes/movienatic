package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.presentation.adapter.EmptyAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.WaitingAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.genre.FetchGenresErrorAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.genre.MoviesByGenreAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.movie.MovieErrorConnectionAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.movie.MovieListItemAdapterDelegate
import dagger.Module
import dagger.Provides

@Module
class AdapterDelegateModule {
    @Provides
    fun providesMovieListItemAdapterDelegate() =
        MovieListItemAdapterDelegate()

    @Provides
    fun providesMovieErrorConnectionAdapterDelegate() =
        MovieErrorConnectionAdapterDelegate()

    @Provides
    fun providesWaitingAdapterDelegate() =
        WaitingAdapterDelegate()

    @Provides
    fun providesEmptyAdapterDelegate() =
        EmptyAdapterDelegate()

    @Provides
    fun providesMoviesByGenreAdapterDelegate() =
        MoviesByGenreAdapterDelegate()

    @Provides
    fun providesFetchGenresErrorAdapterDelegate() =
        FetchGenresErrorAdapterDelegate()
}