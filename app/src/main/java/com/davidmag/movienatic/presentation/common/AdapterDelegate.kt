package com.davidmag.movienatic.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<T: PresentationObject, Q: RecyclerView.ViewHolder> {
    fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): Q

    fun onBindViewHolder(
        items : List<T>,
        holder: Q,
        position: Int
    )

    fun getViewType() : Int
}