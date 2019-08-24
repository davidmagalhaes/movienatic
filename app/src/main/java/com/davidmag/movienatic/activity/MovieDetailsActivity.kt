package com.davidmag.movienatic.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProviders
import com.davidmag.movienatic.BaseActivity
import com.davidmag.movienatic.R
import com.davidmag.movienatic.repository.MovieRepository
import com.davidmag.movienatic.util.DateUtils
import com.davidmag.movienatic.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_movie_details)

        if(intent.hasExtra("movie")){
            val movieLiveData = MovieRepository.findMovie(intent.getIntExtra("movie", -1))


            movie_title.text = movieLiveData.value?.data?.title
            movie_description.text = movieLiveData.value?.data?.originalLanguage
            movie_releaseDate.text = movieLiveData.value?.data?.releaseDate?.let { DateUtils.dateWordsFormat(it) }
            movie_sinopse.text = movieLiveData.value?.data?.overview

        }
    }
}