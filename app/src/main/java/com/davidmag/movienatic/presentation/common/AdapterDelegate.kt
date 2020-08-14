package com.davidmag.movienatic.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

abstract class AdapterDelegate<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    lateinit var inflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    @Inject
    lateinit var viewTypeDelegateMap: List<ViewTypeDelegate<T>>


}