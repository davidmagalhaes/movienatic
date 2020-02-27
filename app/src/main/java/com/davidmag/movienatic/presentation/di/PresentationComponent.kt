package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.infrastructure.di.DatasourceModule
import com.davidmag.movienatic.infrastructure.di.NetworkModule
import com.davidmag.movienatic.infrastructure.di.RepositoryModule
import com.davidmag.movienatic.infrastructure.di.UseCaseModule
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.davidmag.movienatic.presentation.activity.MovieDetailsActivity
import com.davidmag.movienatic.presentation.activity.MovieListTabFragment
import com.davidmag.movienatic.presentation.activity.MovieTabHostActivity
import dagger.BindsInstance



@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatasourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface PresentationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): PresentationComponent
    }

    fun inject(movieTabHostActivity: MovieTabHostActivity)
    fun inject(movieListTabFragment: MovieListTabFragment)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}