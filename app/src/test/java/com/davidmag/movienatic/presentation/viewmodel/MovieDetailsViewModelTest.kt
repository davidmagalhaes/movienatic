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

    }
}