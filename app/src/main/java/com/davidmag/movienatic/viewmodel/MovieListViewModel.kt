package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import com.davidmag.movienatic.repository.ResourceStatus
import kotlinx.coroutines.Deferred

class MovieListViewModel : ViewModel() {
    val movies = MediatorLiveData<Resource<List<Movie>>>()

    var loading = false
    var queryExhausted = false
    var pageNumber = 1

    fun getMovies() : LiveData<Resource<List<Movie>>> {
        return MovieRepository.getUpcomingMovies()
    }

    fun lookupUpcomingMovies() {
        val upcomingMovies = MovieRepository.getUpcomingMovies(false, 1)

        movies.addSource(upcomingMovies){
            if(it.status == ResourceStatus.SUCCESS || it.status == ResourceStatus.ERROR){
                movies.removeSource(upcomingMovies)
            }

            movies.value = it
        }
    }

    fun searchNextPage(){
        if(!loading && !queryExhausted){
            pageNumber++
            executeSearchNextPage()
        }
    }

    private fun executeSearchNextPage(){

    }
}