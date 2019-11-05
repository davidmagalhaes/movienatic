package com.davidmag.movienatic.presentation.activity

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.toolbar
import kotlinx.android.synthetic.main.activity_movie_tabhost.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel : MovieDetailsViewModel

    private var imageConfigs : ImageConfigs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPresentationComponent.builder().
            application(App.instance).
            build().inject(this)

        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(
//            getDrawable(R.drawable.ic_arrow_back_black_24dp)?.apply {
//                setTint(resources.getColor(R.color.text_color_primary))
//            }
//        )
//        supportActionBar?.

        val movieId = intent.getIntExtra("id", -1)

        viewModel = initViewModel { viewModel }

        viewModel.init(movieId).observe(this, Observer {  })

        viewModel.getImageConfigs()
        viewModel.getMovies(movieId)

        viewModel.imageConfigs.observe(this, Observer {
            imageConfigs = it[0]
        })

        viewModel.movies.observe(this, Observer {
            val movie = it[0]

            supportActionBar?.title = movie.title

            movie.backdropPath?.let {posterPath ->
                imageConfigs?.let {
                    GlideApp.with(this).
                        load("${it.secureBaseUrl}w300${posterPath}").
                        diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                        placeholder(R.drawable.ic_launcher_background).
                        into(cover)
                }
            }

            movie.title?.let {
                movie_title.text = it
            }

            movie.overview?.let {
                movie_sinopse.text = it
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}