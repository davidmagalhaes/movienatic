package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.data.repository.MovieRepositoryImpl
import io.mockk.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieListViewModelTest  {

    @Test
    fun testLookupUpcomingMovies() {
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        val resource = Resource.loading(null)

        mockkObject(MovieRepositoryImpl) {
            every { MovieRepositoryImpl.getUpcomingMovies(any()) } returns liveData.apply { value = Resource.loading(null) }

            val viewModel = MovieListViewModel()

            viewModel.lookupUpcomingMovies()

            assert(viewModel.movies.hasObservers())
            assert(viewModel.movies.value == resource)

            verify {
                MovieRepositoryImpl.getUpcomingMovies(1)
            }
        }
    }

    @Test
    fun testSearchNextPage(){
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        //val resource = Resource.loading(null)

        mockkObject(MovieRepositoryImpl) {
            every { MovieRepositoryImpl.getUpcomingMovies(any()) } returns liveData.apply { value = Resource.loading(null) }

            val viewModel = MovieListViewModel()

            viewModel.lookupUpcomingMovies()
            viewModel.searchNextPage()
            viewModel.searchNextPage()

            verifySequence {
                MovieRepositoryImpl.getUpcomingMovies(1)
                MovieRepositoryImpl.getUpcomingMovies(2)
                MovieRepositoryImpl.getUpcomingMovies(3)
            }
        }
    }

}