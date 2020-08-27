package com.davidmag.movienatic.presentation.adapter.movie

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.dto.MoviePresentation
import kotlinx.android.synthetic.main.activity_movie_list_item.view.*

class MovieViewHolder(
    view : View,
    private val movieClickListener: MovieClickListener
) : RecyclerView.ViewHolder(view), View.OnClickListener {
    var coverImg : ImageView? = null

    init {
        coverImg = view.movie_list_item_image
    }

    fun bind(movie: MoviePresentation, imageConfigs : ImageConfigs?){
        movie.posterPath?.let {posterPath ->
            imageConfigs?.let {
                GlideApp.with(itemView.context).
                load("${it.secureBaseUrl}w300${posterPath}").
                diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                placeholder(R.drawable.ic_launcher_background).
                into(coverImg!!)
            }
        }

        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        movieClickListener.onMovieClick(p0!!, adapterPosition)
    }
}