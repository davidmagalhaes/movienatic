package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import kotlinx.coroutines.Deferred

class MovieListViewModel : ViewModel() {
    val movies = MediatorLiveData<List<Movie>>()


    fun getMovies() : LiveData<Resource<List<Movie>>> {
        return MovieRepository.getUpcomingMovies()
    }
}