package com.davidmag.movienatic.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<Q: RecyclerView.ViewHolder> {
    val supportedViewTypes : List<Int>

    fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
        extras: Map<String, Any?>
    ): Q

    fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items : List<PresentationObject>,
        holder: Q,
        position: Int,
        extras : Map<String, Any?>
    )
}