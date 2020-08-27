package com.davidmag.movienatic.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.common.AdapterDelegate
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.MoviePresentation

class MovieListItemAdapterDelegate : AdapterDelegate<MovieViewHolder> {

    companion object {
        const val EXTRA_CLICK_LISTENER = "MovieListItemAdapterDelegate.EXTRA_CLICK_LISTENER"
        const val EXTRA_IMAGE_CONFIG = "MovieListItemAdapterDelegate.EXTRA_IMAGE_CONFIG"
    }

    override val supportedViewTypes = listOf(
        PresentationObject.VIEWTYPE_CONTENT
    )

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras: Map<String, Any?>
    ): MovieViewHolder {
        return MovieViewHolder(
            inflater.inflate(
                R.layout.activity_home_movie_list_item,
                parent,
                false
            ),
            extras[EXTRA_CLICK_LISTENER] as MovieClickListener
        )
    }

    override fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        holder: MovieViewHolder,
        position: Int,
        extras: Map<String, Any?>
    ) {
        holder.bind(
            items[position] as MoviePresentation,
            extras[EXTRA_IMAGE_CONFIG] as ImageConfigs?
        )
    }
}