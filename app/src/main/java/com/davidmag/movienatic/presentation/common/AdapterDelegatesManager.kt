package com.davidmag.movienatic.presentation.common

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

public class AdapterDelegatesManager {

    private val adapterDelegateArray =
        SparseArray<AdapterDelegate<PresentationObject, RecyclerView.ViewHolder>>()

    constructor(
        adapterDelegateList : List<AdapterDelegate<in PresentationObject, in RecyclerView.ViewHolder>>
    ) {
        adapterDelegateList.forEach {
            addDelegate(it)
        }
    }

    fun addDelegate(
        adapterDelegate: AdapterDelegate<in PresentationObject, in RecyclerView.ViewHolder>
    ) {
        adapterDelegateArray.put(
            adapterDelegate.getViewType(),
            adapterDelegate
        )
    }

    fun onCreateViewHolder(inflater: LayoutInflater, parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder{
        return adapterDelegateArray[viewType].onCreateViewHolder(inflater, parent, viewType)
    }

    fun onBindViewHolder(items: List<PresentationObject>, position : Int, viewHolder : RecyclerView.ViewHolder) {
        return adapterDelegateArray[items[position].viewType].onBindViewHolder(items, viewHolder, position)
    }
}