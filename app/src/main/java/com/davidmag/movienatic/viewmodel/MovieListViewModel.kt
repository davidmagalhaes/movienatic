package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import kotlinx.coroutines.Deferred

class MovieListViewModel : ViewModel() {
    val movies = MediatorLiveData<List<Movie>>()

    fun getMovies() : LiveData<List<Movie>?> {

        MovieRepository.getUpcomingMovies()

        return MovieRepository.getUpcomingMovies()
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>?> {
        return MovieRepository.getUpcomingMovies(page, language, region)
    }
}