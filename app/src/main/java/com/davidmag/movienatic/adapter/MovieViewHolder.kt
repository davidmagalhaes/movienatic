package com.davidmag.movienatic.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_movie_list_item.view.*

class MovieViewHolder(view : View, private val movieClickListener: MovieClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    var coverImg : ImageView? = null
    var titleTxt : TextView? = null
    var releaseDateTxt : TextView? = null

    init {
        coverImg = view.movie_list_item_image
        titleTxt = view.movie_list_item_title
        releaseDateTxt = view.movie_list_item_releasedate
    }

    override fun onClick(p0: View?) {
        movieClickListener.onMovieClick(p0!!, adapterPosition)
    }
}