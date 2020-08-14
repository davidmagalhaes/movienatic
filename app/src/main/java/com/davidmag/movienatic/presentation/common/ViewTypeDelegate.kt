package com.davidmag.movienatic.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewTypeDelegate<T: PresentationObject> {
    fun <Q: RecyclerView.ViewHolder> onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): Q

    fun <Q: RecyclerView.ViewHolder> onBindViewHolder(
        holder: Q,
        position: Int
    )

    fun getViewType() : Int
}