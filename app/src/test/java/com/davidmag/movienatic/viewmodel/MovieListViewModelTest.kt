package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieListViewModelTest  {

    @Test
    fun testLookupUpcomingMovies() {
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        val resource = Resource.loading(null)

        mockkObject(MovieRepository) {
            every { MovieRepository.getUpcomingMovies(any()) } returns liveData.apply { value = Resource.loading(null) }

            val viewModel = MovieListViewModel()

            viewModel.lookupUpcomingMovies()

            assert(viewModel.movies.hasObservers())
            assert(viewModel.movies.value == resource)

            verify {
                MovieRepository.getUpcomingMovies(1)
            }
        }
    }

    @Test
    fun testSearchNextPage(){
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        //val resource = Resource.loading(null)

        mockkObject(MovieRepository) {
            every { MovieRepository.getUpcomingMovies(any()) } returns liveData.apply { value = Resource.loading(null) }

            val viewModel = MovieListViewModel()

            viewModel.lookupUpcomingMovies()
            viewModel.searchNextPage()
            viewModel.searchNextPage()

            verifySequence {
                MovieRepository.getUpcomingMovies(1)
                MovieRepository.getUpcomingMovies(2)
                MovieRepository.getUpcomingMovies(3)
            }
        }
    }

}