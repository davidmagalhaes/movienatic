package com.davidmag.movienatic.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.AdapterDelegate

class EmptyAdapterDelegate() : AdapterDelegate<LoadingViewHolder> {

    override val supportedViewTypes = listOf(
        PresentationObject.VIEWTYPE_EMPTY
    )

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras : Map<String, Any?>
    ): LoadingViewHolder {
        return LoadingViewHolder(
            inflater.inflate(
                R.layout.recycler_empty,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        holder: LoadingViewHolder,
        position: Int,
        extras : Map<String, Any?>
    ) {

    }
}