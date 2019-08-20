package com.davidmag.movienatic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmag.movienatic.GlideApp
import com.davidmag.movienatic.R
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.util.Constants
import com.davidmag.movienatic.util.DateUtils
import io.realm.Realm

class MovieRecyclerViewAdapter(private val movieClickListener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList : List<Movie> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_movie_list_item, parent, false)
        return MovieViewHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        movie.posterPath?.let {posterPath ->
            Realm.getDefaultInstance().use {realm ->
                val imageConfigs = realm.where(ImageConfigs::class.java).findFirst()

                imageConfigs?.let {
                    GlideApp.with(holder.itemView.context).
                        load("${it.secureBaseUrl}w300${posterPath}").
                        diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                        placeholder(R.drawable.ic_launcher_background).
                        into(holder.coverImg!!)
                }
            }
        }

        holder.titleTxt?.text = movie.title

        movie.releaseDate?.let {
            holder.releaseDateTxt?.text = DateUtils.dateWordsFormat(it)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}