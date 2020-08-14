package com.davidmag.movienatic.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.presentation.common.PresentationObject

abstract class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(presentationObject: PresentationObject)
}