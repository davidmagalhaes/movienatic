package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource

class MovieDetailsViewModel : ViewModel() {

    val movie = MediatorLiveData<Resource<Movie>>()

    fun findMovie(id : Int){
        val liveData = MovieRepository.findMovie(id)

        movie.addSource(liveData){
            movie.removeSource(liveData)
            movie.value = it

            it.data!!.addChangeListener<Movie> {res, change ->
                movie.value = Resource.success(res)
            }
        }
    }
}