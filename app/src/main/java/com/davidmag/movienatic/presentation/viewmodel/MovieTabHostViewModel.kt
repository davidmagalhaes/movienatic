package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.domain.usecase.*
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieTabHostViewModel(
    private val getImageConfigsUseCase: GetImageConfigsUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val updateImageConfigsUseCase: UpdateImageConfigsUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    val imageConfigs = MediatorLiveData<List<ImageConfigs>>()
    val genres = MediatorLiveData<List<Genre>>()

    fun updateImageConfigs() : LiveData<*> {
        return LiveDataReactiveStreams.fromPublisher(
            updateImageConfigsUseCase.execute().toFlowable().
                observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun searchMovies(query : String) : LiveData<List<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(
            searchMoviesUseCase.execute(query).toFlowable().
                observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun fetchGenres() {
        val source = LiveDataReactiveStreams.fromPublisher(getGenresUseCase.execute().
            toFlowable().
            observeOn(AndroidSchedulers.mainThread()))

        genres.addSource(source){
            genres.removeSource(source)
            genres.postValue(it)
        }
    }

    fun getImageConfigs() {
        val source = LiveDataReactiveStreams.fromPublisher(getImageConfigsUseCase.execute().
            observeOn(AndroidSchedulers.mainThread()))

        imageConfigs.addSource(source){
            imageConfigs.postValue(it)
        }
    }
}