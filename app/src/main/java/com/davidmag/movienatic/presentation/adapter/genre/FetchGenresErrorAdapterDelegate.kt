package com.davidmag.movienatic.presentation.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.genre.GenreErrorViewHolder
import com.davidmag.movienatic.presentation.adapter.genre.MovieByGenreViewHolder
import com.davidmag.movienatic.presentation.common.AdapterDelegate
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import com.davidmag.movienatic.presentation.dto.MoviePresentation

class FetchGenresErrorAdapterDelegate : AdapterDelegate<GenreErrorViewHolder> {
    override val supportedViewTypes = listOf(
        PresentationObject.VIEWTYPE_ERROR
    )

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras: Map<String, Any?>
    ): GenreErrorViewHolder {
        return GenreErrorViewHolder(
            inflater.inflate(
                R.layout.recycler_connection_error,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        holder: GenreErrorViewHolder,
        position: Int,
        extras: Map<String, Any?>
    ) {

    }

}