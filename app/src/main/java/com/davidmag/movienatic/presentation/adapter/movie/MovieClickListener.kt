package com.davidmag.movienatic.presentation.adapter.movie

import android.view.View

interface MovieClickListener {
    fun onMovieClick(v : View, pos : Int)
}