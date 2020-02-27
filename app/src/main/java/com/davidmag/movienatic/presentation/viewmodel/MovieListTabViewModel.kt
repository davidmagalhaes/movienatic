package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.*
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MovieListTabViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase
) : ViewModel() {

    val movies = MediatorLiveData<List<Movie>>()
    val imageConfigs = MediatorLiveData<List<ImageConfigs>>()

    fun getMovies(genreId : Int)  {
        val source = LiveDataReactiveStreams.fromPublisher(
            getMoviesUseCase.execute(genreId = genreId).observeOn(AndroidSchedulers.mainThread()))

        movies.addSource(source){
            movies.postValue(it)
        }
    }

    fun getImageConfigs() {
        val source = LiveDataReactiveStreams.fromPublisher(
            getImageConfigsUseCase.execute().observeOn(AndroidSchedulers.mainThread()))

        imageConfigs.addSource(source){
            imageConfigs.postValue(it)
        }
    }

    fun updateMovieList(genreId : Int) : LiveData<*> {
        val source = LiveDataReactiveStreams.fromPublisher(fetchMoviesUseCase.execute(genreId).
            observeOn(AndroidSchedulers.mainThread()).toFlowable())

        val mediator = MediatorLiveData<List<Movie>>()

        mediator.addSource(source){
            mediator.removeSource(source)
        }

        return mediator
    }
}