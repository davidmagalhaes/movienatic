package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.movie.MovieResourceClient
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.lang.Exception

object MovieRepository {
    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>> {
        return MovieResourceClient.getUpcomingMovies(page, language, region)
    }

    fun getMovies() : LiveData<List<Movie>?> {
        return MovieResourceClient.movies
    }
}