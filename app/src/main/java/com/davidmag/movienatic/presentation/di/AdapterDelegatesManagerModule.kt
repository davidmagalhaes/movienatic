package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.presentation.adapter.EmptyAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.WaitingAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.genre.FetchGenresErrorAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.genre.MoviesByGenreAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.movie.MovieErrorConnectionAdapterDelegate
import com.davidmag.movienatic.presentation.adapter.movie.MovieListItemAdapterDelegate
import com.davidmag.movienatic.presentation.common.AdapterDelegatesManager
import com.davidmag.movienatic.presentation.common.GroupedAdapterDelegates
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class AdapterDelegatesManagerModule {
    @Provides @IntoMap
    @ClassKey(MoviePresentation::class)
    fun providesDelegatesForMoviePresentation(
        movieErrorConnectionAdapterDelegate: MovieErrorConnectionAdapterDelegate,
        movieListItemAdapterDelegate: MovieListItemAdapterDelegate
    ) = GroupedAdapterDelegates(
        movieErrorConnectionAdapterDelegate,
        movieListItemAdapterDelegate
    )

    @Provides @IntoMap
    @ClassKey(GenrePresentation::class)
    fun providesDelegatesForGenrePresentation(
        moviesByGenreAdapterDelegate: MoviesByGenreAdapterDelegate,
        fetchGenresErrorAdapterDelegate: FetchGenresErrorAdapterDelegate
    ) = GroupedAdapterDelegates(
        moviesByGenreAdapterDelegate,
        fetchGenresErrorAdapterDelegate
    )

    @Provides @IntoMap
    @ClassKey(PresentationObject::class)
    fun providesDelegatesForPresentationObject(
        emptyAdapterDelegate: EmptyAdapterDelegate,
        waitingAdapterDelegate: WaitingAdapterDelegate
    ) = GroupedAdapterDelegates(
        emptyAdapterDelegate,
        waitingAdapterDelegate
    )

    @PresentationScope @Provides
    fun providesAdapterDelegatesManager(
        delegatesMap : Map<Class<*>, GroupedAdapterDelegates>
    ) = AdapterDelegatesManager(delegatesMap)
}