package com.davidmag.movienatic.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.common.BaseActivity
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.initViewModel
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

        presentationComponent.inject(this)

        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = initViewModel { viewModel }

        viewModel.imageConfigs.observe(this, Observer {
            when(it.viewType) {
                PresentationObject.DEFAULT_VIEWTYPE_CONTENT -> imageConfigs = it.value
                PresentationObject.DEFAULT_VIEWTYPE_ERROR   -> it.exception?.printStackTrace()
            }
        })

        viewModel.movie.observe(this, Observer {
            when(it.viewType) {
                PresentationObject.DEFAULT_VIEWTYPE_ERROR   -> it.exception?.printStackTrace()
                PresentationObject.DEFAULT_VIEWTYPE_CONTENT -> {
                    val movie = it!!

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
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}