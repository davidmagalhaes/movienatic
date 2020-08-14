package com.davidmag.movienatic.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.common.PresentationObject

class HomeRecyclerAdapter(
    private val context : Context,
    private val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<HomeViewHolder>() {

    var elementList = arrayListOf<PresentationObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when(viewType) {
            PresentationObject.VIEWTYPE_ERROR -> GenreErrorViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.activity_home_connection_error,
                    parent,
                    false
                )
            )
            PresentationObject.VIEWTYPE_WAITING -> LoadingViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.activity_home_movie_list_waiting,
                    parent,
                    false
                )
            )
            else -> HomeGenreViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.activity_home_genre_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(supportFragmentManager, elementList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return elementList[position].viewType
    }
}