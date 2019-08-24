package com.davidmag.movienatic.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.util.DateUtils
import com.davidmag.movienatic.viewmodel.MovieDetailsViewModel
import com.google.android.material.chip.Chip
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        if(intent.hasExtra("movie")){
            viewModel.findMovie(intent.getIntExtra("movie", -1))

            viewModel.movie.observe(this, Observer {

                it.data?.backdropPath?.let {backdrop ->
                    Realm.getDefaultInstance().use { realm ->
                        val imageConfigs = realm.where(ImageConfigs::class.java).findFirst()

                        imageConfigs?.let {
                            GlideApp.with(this).
                                load("${it.secureBaseUrl}w500${backdrop}").
                                diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                                placeholder(R.drawable.ic_launcher_background).
                                into(cover)
                        }
                    }
                }

                it.data?.genres?.forEach{
                    val chip = layoutInflater.inflate(R.layout.chip_genre, movie_genres, false) as Chip

                    chip.text = it.name

                    (movie_genres as ViewGroup).addView(chip)
                }

                movie_title.text = it.data?.title
                movie_releaseDate.text = it.data?.releaseDate?.let { DateUtils.dateWordsFormat(it) }
                movie_sinopse.text = it.data?.overview
            })
        }
    }

    override fun onBackPressed() {
        finish()
    }
}