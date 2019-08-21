package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.movie.MovieApiClient
import kotlinx.coroutines.Deferred

object MovieRepository {
    val movies = MediatorLiveData<List<Movie>>()

    init {
        movies.addSource(MovieApiClient.movies, movies::setValue)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>> {
        return MovieApiClient.getUpcomingMovies(page, language, region)
    }

    fun getMovies() : LiveData<List<Movie>?> {
        return MovieApiClient.movies
    }
}