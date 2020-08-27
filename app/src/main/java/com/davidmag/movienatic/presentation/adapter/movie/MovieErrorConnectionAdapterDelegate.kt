package com.davidmag.movienatic.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.common.AdapterDelegate
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.MoviePresentation

class MovieErrorConnectionAdapterDelegate : AdapterDelegate<RecyclerView.ViewHolder> {

    override val supportedViewTypes = listOf(
        MoviePresentation.VIEWTYPE_ERROR_CONNECTION
    )

    override fun onCreateViewHolder (
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras: Map<String, Any?>
    ) : RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.recycler_connection_error,
                parent,
                false
            )
        ){}
    }

    override fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        holder: RecyclerView.ViewHolder,
        position: Int,
        extras: Map<String, Any?>
    ) {
        TODO("Not yet implemented")
    }
}