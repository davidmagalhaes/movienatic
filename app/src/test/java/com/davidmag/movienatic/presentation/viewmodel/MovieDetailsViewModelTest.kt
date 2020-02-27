package com.davidmag.movienatic.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.domain.model.Movie
import com.davidmag.movienatic.data.repository.MovieRepositoryImpl
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

        mockkObject(MovieRepositoryImpl){
            val viewModel = MovieDetailsViewModel()

            liveData.value = resource

            every { MovieRepositoryImpl.findMovie(any()) } answers { liveData }

            viewModel.findMovie(1)

            assert(viewModel.movie.hasObservers())
            assert(viewModel.movie.value == resource)

            verify {
                MovieRepositoryImpl.findMovie(1)
            }
        }
    }
}