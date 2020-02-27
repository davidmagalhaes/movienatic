package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.FetchMovieDetailsByIdUseCase
import com.davidmag.movienatic.domain.usecase.GetImageConfigsUseCase
import com.davidmag.movienatic.domain.usecase.GetMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val fetchMovieDetailsByIdUseCase: FetchMovieDetailsByIdUseCase,
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val movies = MediatorLiveData<List<Movie>>()
    val imageConfigs = MediatorLiveData<List<ImageConfigs>>()

    fun getMovies(id: Int)  {
        val source = LiveDataReactiveStreams.fromPublisher(
            getMoviesUseCase.execute(id = id).observeOn(AndroidSchedulers.mainThread()))

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

    fun init(id : Int) : LiveData<*> {
        val source = LiveDataReactiveStreams.fromPublisher(
            fetchMovieDetailsByIdUseCase.execute(id).toFlowable()
        )

        val mediator = MediatorLiveData<Any>()

        mediator.addSource(source){
            mediator.removeSource(source)
        }

        return mediator
    }

}