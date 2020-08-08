package com.davidmag.movienatic.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.dto.MoviePresentation

class MovieRecyclerViewAdapter(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    var imageConfigs : ImageConfigs? = null
    var movieList : List<MoviePresentation> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return movieList[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_movie_list_item, parent, false)
        return MovieViewHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        movie.posterPath?.let {posterPath ->
            imageConfigs?.let {
                GlideApp.with(holder.itemView.context).
                    load("${it.secureBaseUrl}w300${posterPath}").
                    diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                    placeholder(R.drawable.ic_launcher_background).
                    into(holder.coverImg!!)
            }
        }

        holder.itemView.setOnClickListener(holder)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun getItem(position: Int): MoviePresentation {
        return movieList[position]
    }
}