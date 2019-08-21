package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.movie.MovieResourceClient
import io.realm.Realm
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.lang.Exception

object MovieRepository {
    val movies = MediatorLiveData<List<Movie>>()

    init {
        movies.addSource(MovieResourceClient.movies, movies::setValue)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : Deferred<List<Movie>> {
        return MovieResourceClient.getUpcomingMovies(page, language, region)
    }

    fun getMovies() : LiveData<List<Movie>?> {
        return MovieResourceClient.movies
    }
}