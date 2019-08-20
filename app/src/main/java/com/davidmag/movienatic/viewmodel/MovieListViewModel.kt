package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import kotlinx.coroutines.CompletableDeferred

class MovieListViewModel : ViewModel() {
    fun getMovies() : LiveData<List<Movie>?> {
        return MovieRepository.getMovies()
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : CompletableDeferred<List<Movie>?> {
        return MovieRepository.getUpcomingMovies(page, language, region)
    }
}