package com.davidmag.movienatic.presentation.adapter.movie

import androidx.fragment.app.FragmentActivity
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.common.DelegatedAdapter
import com.davidmag.movienatic.presentation.dto.MoviePresentation

class MovieRecyclerViewAdapter(
    context: FragmentActivity,
    movieClickListener: MovieClickListener,
    var movieList : List<MoviePresentation> = ArrayList(),
    var imageConfigs: ImageConfigs? = null
) : DelegatedAdapter(context, movieList, hashMapOf(
    MovieListItemAdapterDelegate.EXTRA_IMAGE_CONFIG to imageConfigs,
    MovieListItemAdapterDelegate.EXTRA_CLICK_LISTENER to movieClickListener
))