package com.davidmag.movienatic.presentation.fragmentactivity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.initViewModel
import com.davidmag.movienatic.presentation.di.presentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {

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
                PresentationObject.VIEWTYPE_ERROR   -> it.exception?.printStackTrace()
                PresentationObject.VIEWTYPE_CONTENT -> {
                    val oldImageConfigs = imageConfigs
                    imageConfigs = it.value

                    if(oldImageConfigs == null){
                        bindMovie()
                    }
                }
            }
        })
    }

    fun bindMovie(){
        viewModel.movie.observe(this, Observer {
            when(it.viewType) {
                PresentationObject.VIEWTYPE_ERROR   -> it.exception?.printStackTrace()
                PresentationObject.VIEWTYPE_CONTENT -> {
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