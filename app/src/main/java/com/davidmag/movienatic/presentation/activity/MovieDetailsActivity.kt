package com.davidmag.movienatic.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.toolbar
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel : MovieDetailsViewModel

    private var imageConfigs : ImageConfigs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPresentationComponent
            .builder()
            .applicationComponent(App.applicationComponent)
            .build()
            .inject(this)

        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getLongExtra("id", -1)

        viewModel = initViewModel { viewModel }

        viewModel.init(movieId).observe(this, Observer {  })

        viewModel.getImageConfigs()
        viewModel.getMovies(movieId)

        viewModel.imageConfigs.observe(this, Observer {
            if(it.isSuccessful()){
                imageConfigs = it.data
            }
            else if(it.hasFailed()) {
                it.error?.printStackTrace()
            }
        })

        viewModel.movie.observe(this, Observer {
            if (it.isSuccessful()) {
                val movie = it.data!!

                supportActionBar?.title = movie.title

                movie.backdropPath?.let { posterPath ->
                    imageConfigs?.let {
                        GlideApp.with(this).load("${it.secureBaseUrl}w500${posterPath}")
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.ic_launcher_background).into(cover)
                    }
                }

                movie.title?.let {
                    movie_title.text = it
                }

                movie.overview?.let {
                    movie_sinopse.text = it
                }
            }
            else {
                it.error?.printStackTrace()
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}