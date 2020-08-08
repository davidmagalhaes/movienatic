package com.davidmag.movienatic.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import com.davidmag.movienatic.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home_genre_item.view.*

class HomeGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.genre_title
    val movieList = itemView.movie_list

    fun bind(viewModel: HomeViewModel, genrePresentation: GenrePresentation){
        title.text = genrePresentation.description


    }
}