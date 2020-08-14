package com.davidmag.movienatic.presentation.adapter.viewtype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.LoadingViewHolder
import com.davidmag.movienatic.presentation.common.ViewTypeDelegate

class LoadingViewTypeDelegate : ViewTypeDelegate<LoadingViewHolder> {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): LoadingViewHolder {
        return LoadingViewHolder(
            inflater.inflate(
                R.layout.activity_home_movie_list_waiting,
                parent,
                false
            )
        )
    }


}