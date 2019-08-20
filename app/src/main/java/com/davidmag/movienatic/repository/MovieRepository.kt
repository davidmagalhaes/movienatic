package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.movie.MovieResourceClient
import kotlinx.coroutines.CompletableDeferred

object MovieRepository {
    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : CompletableDeferred<List<Movie>?> {
        return MovieResourceClient.getUpcomingMovies(page, language, region)
    }

    fun getMovies() : LiveData<List<Movie>?> {
        return MovieResourceClient.movies
    }
}