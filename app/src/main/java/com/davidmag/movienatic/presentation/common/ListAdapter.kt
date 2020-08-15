package com.davidmag.movienatic.presentation.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

abstract class ListAdapter (
    context : Context,
    private val items : List<PresentationObject>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    @Inject
    lateinit var adapterDelegatesManager: AdapterDelegatesManager

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapterDelegatesManager.onCreateViewHolder(
            layoutInflater,
            parent,
            viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapterDelegatesManager.onBindViewHolder(
            items,
            position,
            holder
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }
}