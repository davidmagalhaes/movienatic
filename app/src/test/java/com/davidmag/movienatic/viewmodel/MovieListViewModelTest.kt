package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieListViewModelTest  {

    private val viewModel = MovieListViewModel()

    @Before
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetMovies() {
        val liveData = MutableLiveData<Resource<List<Movie>>>()

        mockkObject(MovieRepository) {
            every { MovieRepository.getUpcomingMovies() } returns liveData

            assert(viewModel.getMovies() == liveData)
        }
    }


}