package com.davidmag.movienatic.presentation.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.genre.MovieByGenreViewHolder
import com.davidmag.movienatic.presentation.common.AdapterDelegate
import com.davidmag.movienatic.presentation.common.PresentationObject

class MoviesByGenreAdapterDelegate : AdapterDelegate<MovieByGenreViewHolder> {
    override val supportedViewTypes = listOf(
        PresentationObject.VIEWTYPE_CONTENT
    )

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras: Map<String, Any?>
    ): MovieByGenreViewHolder {
        return MovieByGenreViewHolder(
            inflater.inflate(
                R.layout.activity_home_genre_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        holder: MovieByGenreViewHolder,
        position: Int,
        extras: Map<String, Any?>
    ) {
        holder.bind(supportFragmentManager, items[position])
    }

}