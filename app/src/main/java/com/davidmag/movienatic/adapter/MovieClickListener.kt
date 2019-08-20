package com.davidmag.movienatic.adapter

import android.view.View

interface MovieClickListener {
    fun onMovieClick(v : View, pos : Int)
}