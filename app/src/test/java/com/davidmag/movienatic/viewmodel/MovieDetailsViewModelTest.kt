package com.davidmag.movienatic.viewmodel

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.repository.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {

    @Before
    fun setup(){
        //MockKAnnotations.init(this)
    }

    @Test
    fun testFindMovie(){
        val liveData = MutableLiveData<Resource<Movie>>()
        val resource = Resource.loading(null)

        mockkObject(MovieRepository){
            val viewModel = MovieDetailsViewModel()

            liveData.value = resource

            every { MovieRepository.findMovie(any()) } answers { liveData }

            viewModel.findMovie(1)

            assert(viewModel.movie.hasObservers())
            assert(viewModel.movie.value == resource)

            verify {
                MovieRepository.findMovie(1)
            }
        }
    }
}