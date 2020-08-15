package com.davidmag.movienatic.presentation.adapter.viewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.LoadingViewHolder
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.AdapterDelegate

class LoadingAdapterDelegate : AdapterDelegate<PresentationObject, LoadingViewHolder> {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): LoadingViewHolder {
        return LoadingViewHolder(
            inflater.inflate(
                R.layout.activity_home_movie_list_waiting,
                parent,
                false
            )
        )
    }

    override fun getViewType(): Int {
        return PresentationObject.VIEWTYPE_WAITING
    }

    override fun onBindViewHolder(
        items: List<PresentationObject>,
        holder: LoadingViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }


}