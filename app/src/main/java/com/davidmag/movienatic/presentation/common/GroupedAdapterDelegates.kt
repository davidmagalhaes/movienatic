package com.davidmag.movienatic.presentation.common

import androidx.recyclerview.widget.RecyclerView

class GroupedAdapterDelegates(
    vararg adapterDelegate: AdapterDelegate<out RecyclerView.ViewHolder>
) : HashSet<AdapterDelegate<out RecyclerView.ViewHolder>>() {
    init {
        addAll(adapterDelegate)
    }
}