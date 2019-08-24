package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import com.davidmag.movienatic.repository.ResourceStatus

class MovieListViewModel : ViewModel() {
    val movies = MediatorLiveData<Resource<List<Movie>>>()

    var loading = false
    var pagesExhausted = false
    var pageNumber = 1

    fun lookupUpcomingMovies(page : Int = 1) {
        val upcomingMovies = MovieRepository.getUpcomingMovies(page)

        movies.addSource(upcomingMovies){
            if(it.status == ResourceStatus.SUCCESS || it.status == ResourceStatus.ERROR){
                movies.removeSource(upcomingMovies)
            }

            movies.value = it
        }
    }

    fun searchNextPage(){
        if(!loading && !pagesExhausted){
            pageNumber++
            lookupUpcomingMovies(pageNumber)
        }
    }
}